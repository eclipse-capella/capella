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
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;

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
 * @see org.polarsys.capella.core.data.pa.PaFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/dsl/2007/dslfactory trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='PhysicalArchitecture aims at defining the system\'s software, middleware and hardware architecture modelling language (close to the OMG\'s Platform Independent Model (PIM) in addition to OMG\'s Platform Model (PM)) using notions close to OMG\'s MARTE Resource concept. It adds the Deployment concern.\r\nThis concern aggregates a lot of concepts regarding the others. A re-engineering of this concern should make sense.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='physical' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 * @generated
 */
public interface PaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pa"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.polarsys.org/capella/core/pa/1.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.polarsys.capella.core.data.pa"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PaPackage eINSTANCE = org.polarsys.capella.core.data.pa.impl.PaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitecturePkgImpl <em>Physical Architecture Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalArchitecturePkgImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalArchitecturePkg()
	 * @generated
	 */
	int PHYSICAL_ARCHITECTURE_PKG = 0;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_EXTENSIONS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__ID = CsPackage.BLOCK_ARCHITECTURE_PKG__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__SID = CsPackage.BLOCK_ARCHITECTURE_PKG__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE_PKG__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__NAME = CsPackage.BLOCK_ARCHITECTURE_PKG__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__INCOMING_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OUTGOING_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__VISIBLE_IN_DOC = CsPackage.BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__VISIBLE_IN_LM = CsPackage.BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__SUMMARY = CsPackage.BLOCK_ARCHITECTURE_PKG__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__DESCRIPTION = CsPackage.BLOCK_ARCHITECTURE_PKG__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__REVIEW = CsPackage.BLOCK_ARCHITECTURE_PKG__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__STATUS = CsPackage.BLOCK_ARCHITECTURE_PKG__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__FEATURES = CsPackage.BLOCK_ARCHITECTURE_PKG__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__APPLIED_REQUIREMENTS = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__CONTAINED_REQUIREMENTS_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__NAMING_RULES = CsPackage.BLOCK_ARCHITECTURE_PKG__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Physical Architecture Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Physical Architectures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Physical Architecture Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_PKG_FEATURE_COUNT = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl <em>Physical Architecture</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalArchitecture()
	 * @generated
	 */
	int PHYSICAL_ARCHITECTURE = 1;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_EXTENSIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__ID = CsPackage.COMPONENT_ARCHITECTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__SID = CsPackage.COMPONENT_ARCHITECTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__NAME = CsPackage.COMPONENT_ARCHITECTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__INCOMING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OUTGOING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__VISIBLE_IN_DOC = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__VISIBLE_IN_LM = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__SUMMARY = CsPackage.COMPONENT_ARCHITECTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__DESCRIPTION = CsPackage.COMPONENT_ARCHITECTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__REVIEW = CsPackage.COMPONENT_ARCHITECTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__STATUS = CsPackage.COMPONENT_ARCHITECTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__FEATURES = CsPackage.COMPONENT_ARCHITECTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__NAMING_RULES = CsPackage.COMPONENT_ARCHITECTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Requirement Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_REQUIREMENT_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_REQUIREMENT_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_DATA_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__ALLOCATED_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATED_ARCHITECTURES;

	/**
	 * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__ALLOCATING_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATING_ARCHITECTURES;

	/**
	 * The feature id for the '<em><b>Owned Physical Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Physical Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Physical Component Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Physical Actor Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Contained Capability Realization Pkg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Contained Physical Function Pkg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Owned Deployments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Owned Logical Architecture Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Allocated Logical Architecture Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Allocated Logical Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Allocating Epbs Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Physical Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_FEATURE_COUNT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl <em>Physical Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalFunction()
	 * @generated
	 */
	int PHYSICAL_FUNCTION = 2;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ID = FaPackage.ABSTRACT_FUNCTION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__SID = FaPackage.ABSTRACT_FUNCTION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__NAME = FaPackage.ABSTRACT_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__SUMMARY = FaPackage.ABSTRACT_FUNCTION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__DESCRIPTION = FaPackage.ABSTRACT_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__REVIEW = FaPackage.ABSTRACT_FUNCTION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__STATUS = FaPackage.ABSTRACT_FUNCTION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__FEATURES = FaPackage.ABSTRACT_FUNCTION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__APPLIED_REQUIREMENTS = FaPackage.ABSTRACT_FUNCTION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTION__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTION__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CONTAINED_REQUIREMENTS_TRACES = FaPackage.ABSTRACT_FUNCTION__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__NAMING_RULES = FaPackage.ABSTRACT_FUNCTION__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INVOLVING_INVOLVEMENTS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IS_ABSTRACT = FaPackage.ABSTRACT_FUNCTION__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IS_STATIC = FaPackage.ABSTRACT_FUNCTION__IS_STATIC;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__VISIBILITY = FaPackage.ABSTRACT_FUNCTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Abstract Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ABSTRACT_TYPE = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__TYPE = FaPackage.ABSTRACT_FUNCTION__TYPE;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ORDERED = FaPackage.ABSTRACT_FUNCTION__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__UNIQUE = FaPackage.ABSTRACT_FUNCTION__UNIQUE;

	/**
	 * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__MIN_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MIN_INCLUSIVE;

	/**
	 * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__MAX_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MAX_INCLUSIVE;

	/**
	 * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_DEFAULT_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_MIN_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_MAX_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_NULL_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_NULL_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_MIN_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_CARD;

	/**
	 * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_MIN_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_MAX_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_CARD;

	/**
	 * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_MAX_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_LENGTH;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__FINAL = FaPackage.ABSTRACT_FUNCTION__FINAL;

	/**
	 * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__AGGREGATION_KIND = FaPackage.ABSTRACT_FUNCTION__AGGREGATION_KIND;

	/**
	 * The feature id for the '<em><b>Is Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IS_DERIVED = FaPackage.ABSTRACT_FUNCTION__IS_DERIVED;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IS_READ_ONLY = FaPackage.ABSTRACT_FUNCTION__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IS_PART_OF_KEY = FaPackage.ABSTRACT_FUNCTION__IS_PART_OF_KEY;

	/**
	 * The feature id for the '<em><b>Association</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ASSOCIATION = FaPackage.ABSTRACT_FUNCTION__ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__REPRESENTING_INSTANCE_ROLES = FaPackage.ABSTRACT_FUNCTION__REPRESENTING_INSTANCE_ROLES;

	/**
	 * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_CHAINS;

	/**
	 * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IN_ACTIVITY_PARTITION = FaPackage.ABSTRACT_FUNCTION__IN_ACTIVITY_PARTITION;

	/**
	 * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IN_INTERRUPTIBLE_REGION = FaPackage.ABSTRACT_FUNCTION__IN_INTERRUPTIBLE_REGION;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IN_STRUCTURED_NODE = FaPackage.ABSTRACT_FUNCTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OUTGOING = FaPackage.ABSTRACT_FUNCTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INCOMING = FaPackage.ABSTRACT_FUNCTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Owned Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_HANDLERS = FaPackage.ABSTRACT_FUNCTION__OWNED_HANDLERS;

	/**
	 * The feature id for the '<em><b>Local Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__LOCAL_PRECONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Local Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__LOCAL_POSTCONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CONTEXT = FaPackage.ABSTRACT_FUNCTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INPUTS = FaPackage.ABSTRACT_FUNCTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OUTPUTS = FaPackage.ABSTRACT_FUNCTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ARGUMENTS = FaPackage.ABSTRACT_FUNCTION__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__RESULTS = FaPackage.ABSTRACT_FUNCTION__RESULTS;

	/**
	 * The feature id for the '<em><b>Behavior</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__BEHAVIOR = FaPackage.ABSTRACT_FUNCTION__BEHAVIOR;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ABSTRACT_TYPED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__KIND = FaPackage.ABSTRACT_FUNCTION__KIND;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CONDITION = FaPackage.ABSTRACT_FUNCTION__CONDITION;

	/**
	 * The feature id for the '<em><b>Owned Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>Owned Function Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Functional Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Sub Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__SUB_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__SUB_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>Out Function Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OUT_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>In Function Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__IN_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Component Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocation Blocks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ALLOCATION_BLOCKS = FaPackage.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS;

	/**
	 * The feature id for the '<em><b>Available In States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__AVAILABLE_IN_STATES = FaPackage.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES;

	/**
	 * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INVOLVING_CAPABILITIES = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES;

	/**
	 * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Involving Functional Chains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS;

	/**
	 * The feature id for the '<em><b>Linked State Machine</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__LINKED_STATE_MACHINE = FaPackage.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE;

	/**
	 * The feature id for the '<em><b>Linked Function Specification</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__LINKED_FUNCTION_SPECIFICATION = FaPackage.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Owned Physical Function Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Allocator Physical Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ALLOCATOR_PHYSICAL_ACTORS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Allocator Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__ALLOCATOR_PHYSICAL_COMPONENTS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Realized Logical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Contained Physical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Children Physical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Physical Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionPkgImpl <em>Physical Function Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalFunctionPkgImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalFunctionPkg()
	 * @generated
	 */
	int PHYSICAL_FUNCTION_PKG = 3;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_EXTENSIONS = FaPackage.FUNCTION_PKG__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__ID = FaPackage.FUNCTION_PKG__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__SID = FaPackage.FUNCTION_PKG__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__CONSTRAINTS = FaPackage.FUNCTION_PKG__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_CONSTRAINTS = FaPackage.FUNCTION_PKG__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__NAME = FaPackage.FUNCTION_PKG__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__INCOMING_TRACES = FaPackage.FUNCTION_PKG__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OUTGOING_TRACES = FaPackage.FUNCTION_PKG__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__VISIBLE_IN_DOC = FaPackage.FUNCTION_PKG__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__VISIBLE_IN_LM = FaPackage.FUNCTION_PKG__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__SUMMARY = FaPackage.FUNCTION_PKG__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__DESCRIPTION = FaPackage.FUNCTION_PKG__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__REVIEW = FaPackage.FUNCTION_PKG__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__APPLIED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__STATUS = FaPackage.FUNCTION_PKG__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__FEATURES = FaPackage.FUNCTION_PKG__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__APPLIED_REQUIREMENTS = FaPackage.FUNCTION_PKG__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_TRACES = FaPackage.FUNCTION_PKG__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__CONTAINED_GENERIC_TRACES = FaPackage.FUNCTION_PKG__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__CONTAINED_REQUIREMENTS_TRACES = FaPackage.FUNCTION_PKG__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__NAMING_RULES = FaPackage.FUNCTION_PKG__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS = FaPackage.FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_EXCHANGES = FaPackage.FUNCTION_PKG__OWNED_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS = FaPackage.FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_CATEGORIES = FaPackage.FUNCTION_PKG__OWNED_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Owned Function Specifications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS = FaPackage.FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS;

	/**
	 * The feature id for the '<em><b>Owned Physical Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Physical Function Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTION_PKGS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Physical Function Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_FUNCTION_PKG_FEATURE_COUNT = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl <em>Abstract Physical Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getAbstractPhysicalComponent()
	 * @generated
	 */
	int ABSTRACT_PHYSICAL_COMPONENT = 4;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_EXTENSIONS = CsPackage.SYSTEM_COMPONENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ID = CsPackage.SYSTEM_COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SID = CsPackage.SYSTEM_COMPONENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONSTRAINTS = CsPackage.SYSTEM_COMPONENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_CONSTRAINTS = CsPackage.SYSTEM_COMPONENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__NAME = CsPackage.SYSTEM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS = CsPackage.SYSTEM_COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__INCOMING_TRACES = CsPackage.SYSTEM_COMPONENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OUTGOING_TRACES = CsPackage.SYSTEM_COMPONENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__VISIBLE_IN_DOC = CsPackage.SYSTEM_COMPONENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__VISIBLE_IN_LM = CsPackage.SYSTEM_COMPONENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SUMMARY = CsPackage.SYSTEM_COMPONENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__DESCRIPTION = CsPackage.SYSTEM_COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__REVIEW = CsPackage.SYSTEM_COMPONENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUES = CsPackage.SYSTEM_COMPONENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.SYSTEM_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUES = CsPackage.SYSTEM_COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.SYSTEM_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.SYSTEM_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__STATUS = CsPackage.SYSTEM_COMPONENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__FEATURES = CsPackage.SYSTEM_COMPONENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__APPLIED_REQUIREMENTS = CsPackage.SYSTEM_COMPONENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_TRACES = CsPackage.SYSTEM_COMPONENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES = CsPackage.SYSTEM_COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_REQUIREMENTS_TRACES = CsPackage.SYSTEM_COMPONENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__NAMING_RULES = CsPackage.SYSTEM_COMPONENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__TYPED_ELEMENTS = CsPackage.SYSTEM_COMPONENT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.SYSTEM_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES = CsPackage.SYSTEM_COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.SYSTEM_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS = CsPackage.SYSTEM_COMPONENT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS = CsPackage.SYSTEM_COMPONENT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS = CsPackage.SYSTEM_COMPONENT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.SYSTEM_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_DATA_PKG = CsPackage.SYSTEM_COMPONENT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_STATE_MACHINES = CsPackage.SYSTEM_COMPONENT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ABSTRACT = CsPackage.SYSTEM_COMPONENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS = CsPackage.SYSTEM_COMPONENT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS = CsPackage.SYSTEM_COMPONENT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SUB_GENERALIZATIONS = CsPackage.SYSTEM_COMPONENT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SUPER = CsPackage.SYSTEM_COMPONENT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SUB = CsPackage.SYSTEM_COMPONENT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_FEATURES = CsPackage.SYSTEM_COMPONENT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PROPERTIES = CsPackage.SYSTEM_COMPONENT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_PARTITIONS = CsPackage.SYSTEM_COMPONENT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__REPRESENTING_PARTITIONS = CsPackage.SYSTEM_COMPONENT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_INTERFACES = CsPackage.SYSTEM_COMPONENT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS = CsPackage.SYSTEM_COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__PRODUCE = CsPackage.SYSTEM_COMPONENT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONSUME = CsPackage.SYSTEM_COMPONENT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__SEND = CsPackage.SYSTEM_COMPONENT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__RECEIVE = CsPackage.SYSTEM_COMPONENT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CALL = CsPackage.SYSTEM_COMPONENT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__EXECUTE = CsPackage.SYSTEM_COMPONENT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__WRITE = CsPackage.SYSTEM_COMPONENT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ACCESS = CsPackage.SYSTEM_COMPONENT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ACQUIRE = CsPackage.SYSTEM_COMPONENT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__TRANSMIT = CsPackage.SYSTEM_COMPONENT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_USES = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__USED_INTERFACE_LINKS = CsPackage.SYSTEM_COMPONENT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__USED_INTERFACES = CsPackage.SYSTEM_COMPONENT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS = CsPackage.SYSTEM_COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES = CsPackage.SYSTEM_COMPONENT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_COMPONENTS = CsPackage.SYSTEM_COMPONENT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__ALLOCATING_COMPONENTS = CsPackage.SYSTEM_COMPONENT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__PROVIDED_INTERFACES = CsPackage.SYSTEM_COMPONENT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__REQUIRED_INTERFACES = CsPackage.SYSTEM_COMPONENT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS = CsPackage.SYSTEM_COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PARTS = CsPackage.SYSTEM_COMPONENT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS = CsPackage.SYSTEM_COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH = CsPackage.SYSTEM_COMPONENT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS = CsPackage.SYSTEM_COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.SYSTEM_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS = CsPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = CsPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Data Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__DATA_COMPONENT = CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__DATA_TYPE = CsPackage.SYSTEM_COMPONENT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = CsPackage.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__KIND = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Nature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__NATURE = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Owned Deployment Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Owned Deployment Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Abstract Physical Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl <em>Physical Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponent()
	 * @generated
	 */
	int PHYSICAL_COMPONENT = 5;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ID = ABSTRACT_PHYSICAL_COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SID = ABSTRACT_PHYSICAL_COMPONENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONSTRAINTS = ABSTRACT_PHYSICAL_COMPONENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__NAME = ABSTRACT_PHYSICAL_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__INCOMING_TRACES = ABSTRACT_PHYSICAL_COMPONENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OUTGOING_TRACES = ABSTRACT_PHYSICAL_COMPONENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_COMPONENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_COMPONENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SUMMARY = ABSTRACT_PHYSICAL_COMPONENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DESCRIPTION = ABSTRACT_PHYSICAL_COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__REVIEW = ABSTRACT_PHYSICAL_COMPONENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__STATUS = ABSTRACT_PHYSICAL_COMPONENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__FEATURES = ABSTRACT_PHYSICAL_COMPONENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__APPLIED_REQUIREMENTS = ABSTRACT_PHYSICAL_COMPONENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_TRACES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONTAINED_REQUIREMENTS_TRACES = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__NAMING_RULES = ABSTRACT_PHYSICAL_COMPONENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__TYPED_ELEMENTS = ABSTRACT_PHYSICAL_COMPONENT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION = ABSTRACT_PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG = ABSTRACT_PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_DATA_PKG = ABSTRACT_PHYSICAL_COMPONENT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_STATE_MACHINES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ABSTRACT = ABSTRACT_PHYSICAL_COMPONENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SUB_GENERALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SUPER = ABSTRACT_PHYSICAL_COMPONENT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SUB = ABSTRACT_PHYSICAL_COMPONENT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_FEATURES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONTAINED_PROPERTIES = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PARTITIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__REPRESENTING_PARTITIONS = ABSTRACT_PHYSICAL_COMPONENT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ALLOCATED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__PRODUCE = ABSTRACT_PHYSICAL_COMPONENT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONSUME = ABSTRACT_PHYSICAL_COMPONENT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SEND = ABSTRACT_PHYSICAL_COMPONENT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__RECEIVE = ABSTRACT_PHYSICAL_COMPONENT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CALL = ABSTRACT_PHYSICAL_COMPONENT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__EXECUTE = ABSTRACT_PHYSICAL_COMPONENT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__WRITE = ABSTRACT_PHYSICAL_COMPONENT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ACCESS = ABSTRACT_PHYSICAL_COMPONENT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ACQUIRE = ABSTRACT_PHYSICAL_COMPONENT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__TRANSMIT = ABSTRACT_PHYSICAL_COMPONENT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_INTERFACE_USES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__USED_INTERFACE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__USED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ALLOCATED_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ALLOCATING_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__PROVIDED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__REQUIRED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONTAINED_PARTS = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS = ABSTRACT_PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = ABSTRACT_PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Data Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DATA_COMPONENT = ABSTRACT_PHYSICAL_COMPONENT__DATA_COMPONENT;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DATA_TYPE = ABSTRACT_PHYSICAL_COMPONENT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DEPLOYING_LINKS = ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS;

	/**
	 * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DEPLOYMENT_LINKS = ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__KIND = ABSTRACT_PHYSICAL_COMPONENT__KIND;

	/**
	 * The feature id for the '<em><b>Nature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__NATURE = ABSTRACT_PHYSICAL_COMPONENT__NATURE;

	/**
	 * The feature id for the '<em><b>Owned Deployment Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Deployment Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT = ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT;

	/**
	 * The feature id for the '<em><b>Allocator Configuration Items</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ALLOCATOR_CONFIGURATION_ITEMS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Physical Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Physical Component Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Logical Component Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Logical Component Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Logical Interface Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Sub Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Realized Logical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Allocated Physical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Deployed Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Deploying Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Deploying Physical Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Physical Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_FEATURE_COUNT = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorPkgImpl <em>Physical Actor Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalActorPkgImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalActorPkg()
	 * @generated
	 */
	int PHYSICAL_ACTOR_PKG = 6;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__ID = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__SID = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__NAME = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__SUMMARY = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__DESCRIPTION = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__REVIEW = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__STATUS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__FEATURES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__APPLIED_REQUIREMENTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__CONTAINED_REQUIREMENTS_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__NAMING_RULES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_PROPERTY_VALUE_PKGS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_COMPONENT_EXCHANGES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_FUNCTIONAL_LINKS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_FUNCTIONAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Physical Actor Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Physical Actors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Physical Actor Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_PKG_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl <em>Physical Component Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponentPkg()
	 * @generated
	 */
	int PHYSICAL_COMPONENT_PKG = 7;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__ID = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__SID = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__NAME = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__SUMMARY = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__DESCRIPTION = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__REVIEW = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__STATUS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__FEATURES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__APPLIED_REQUIREMENTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__CONTAINED_REQUIREMENTS_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__NAMING_RULES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_FUNCTIONAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__VISIBILITY = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Associations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_ASSOCIATIONS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_COMPONENTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Physical Component Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Owned Key Parts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Owned Deployments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Physical Component Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_COMPONENT_PKG_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTIONAL_STRUCTURE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl <em>Physical Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalActor()
	 * @generated
	 */
	int PHYSICAL_ACTOR = 8;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ID = ABSTRACT_PHYSICAL_COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SID = ABSTRACT_PHYSICAL_COMPONENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONSTRAINTS = ABSTRACT_PHYSICAL_COMPONENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__NAME = ABSTRACT_PHYSICAL_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__INCOMING_TRACES = ABSTRACT_PHYSICAL_COMPONENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OUTGOING_TRACES = ABSTRACT_PHYSICAL_COMPONENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_COMPONENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_COMPONENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SUMMARY = ABSTRACT_PHYSICAL_COMPONENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__DESCRIPTION = ABSTRACT_PHYSICAL_COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__REVIEW = ABSTRACT_PHYSICAL_COMPONENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__STATUS = ABSTRACT_PHYSICAL_COMPONENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__FEATURES = ABSTRACT_PHYSICAL_COMPONENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__APPLIED_REQUIREMENTS = ABSTRACT_PHYSICAL_COMPONENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_TRACES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONTAINED_GENERIC_TRACES = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONTAINED_REQUIREMENTS_TRACES = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__NAMING_RULES = ABSTRACT_PHYSICAL_COMPONENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__TYPED_ELEMENTS = ABSTRACT_PHYSICAL_COMPONENT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_FUNCTIONAL_ALLOCATION = ABSTRACT_PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_COMPONENT_EXCHANGES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_COMPONENT_EXCHANGE_CATEGORIES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__FUNCTIONAL_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ALLOCATED_FUNCTIONS = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__IN_EXCHANGE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OUT_EXCHANGE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_ABSTRACT_CAPABILITY_PKG = ABSTRACT_PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_INTERFACE_PKG = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_DATA_PKG = ABSTRACT_PHYSICAL_COMPONENT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_STATE_MACHINES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ABSTRACT = ABSTRACT_PHYSICAL_COMPONENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_GENERALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SUPER_GENERALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SUB_GENERALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SUPER = ABSTRACT_PHYSICAL_COMPONENT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SUB = ABSTRACT_PHYSICAL_COMPONENT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_FEATURES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONTAINED_PROPERTIES = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_PARTITIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__REPRESENTING_PARTITIONS = ABSTRACT_PHYSICAL_COMPONENT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_INTERFACE_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__PROVISIONED_INTERFACE_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ALLOCATED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_COMMUNICATION_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__PRODUCE = ABSTRACT_PHYSICAL_COMPONENT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONSUME = ABSTRACT_PHYSICAL_COMPONENT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__SEND = ABSTRACT_PHYSICAL_COMPONENT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__RECEIVE = ABSTRACT_PHYSICAL_COMPONENT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CALL = ABSTRACT_PHYSICAL_COMPONENT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__EXECUTE = ABSTRACT_PHYSICAL_COMPONENT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__WRITE = ABSTRACT_PHYSICAL_COMPONENT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ACCESS = ABSTRACT_PHYSICAL_COMPONENT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ACQUIRE = ABSTRACT_PHYSICAL_COMPONENT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__TRANSMIT = ABSTRACT_PHYSICAL_COMPONENT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_INTERFACE_USES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__USED_INTERFACE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__USED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_INTERFACE_IMPLEMENTATIONS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__IMPLEMENTED_INTERFACE_LINKS = ABSTRACT_PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__IMPLEMENTED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__PROVISIONED_COMPONENT_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__PROVISIONING_COMPONENT_ALLOCATIONS = ABSTRACT_PHYSICAL_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ALLOCATED_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ALLOCATING_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__PROVIDED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__REQUIRED_INTERFACES = ABSTRACT_PHYSICAL_COMPONENT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONTAINED_COMPONENT_PORTS = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONTAINED_PARTS = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__CONTAINED_PHYSICAL_PORTS = ABSTRACT_PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_PHYSICAL_PATH = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_PHYSICAL_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_PHYSICAL_LINK_CATEGORIES = ABSTRACT_PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__INVOLVING_INVOLVEMENTS = ABSTRACT_PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = ABSTRACT_PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Data Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__DATA_COMPONENT = ABSTRACT_PHYSICAL_COMPONENT__DATA_COMPONENT;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__DATA_TYPE = ABSTRACT_PHYSICAL_COMPONENT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__DEPLOYING_LINKS = ABSTRACT_PHYSICAL_COMPONENT__DEPLOYING_LINKS;

	/**
	 * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__DEPLOYMENT_LINKS = ABSTRACT_PHYSICAL_COMPONENT__DEPLOYMENT_LINKS;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__KIND = ABSTRACT_PHYSICAL_COMPONENT__KIND;

	/**
	 * The feature id for the '<em><b>Nature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__NATURE = ABSTRACT_PHYSICAL_COMPONENT__NATURE;

	/**
	 * The feature id for the '<em><b>Owned Deployment Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_DEPLOYMENT_LINKS = ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Deployment Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_DEPLOYMENT_ASPECT = ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT;

	/**
	 * The feature id for the '<em><b>Owned Logical Actor Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Logical Actor Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Allocated Physical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Realized Logical Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Deployed Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Physical Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ACTOR_FEATURE_COUNT = ABSTRACT_PHYSICAL_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalActorRealizationImpl <em>Logical Actor Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.LogicalActorRealizationImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalActorRealization()
	 * @generated
	 */
	int LOGICAL_ACTOR_REALIZATION = 9;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__OWNED_EXTENSIONS = CsPackage.COMPONENT_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__ID = CsPackage.COMPONENT_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__SID = CsPackage.COMPONENT_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__REALIZED_FLOW = CsPackage.COMPONENT_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__INCOMING_TRACES = CsPackage.COMPONENT_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__OUTGOING_TRACES = CsPackage.COMPONENT_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__VISIBLE_IN_DOC = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__VISIBLE_IN_LM = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__SUMMARY = CsPackage.COMPONENT_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__DESCRIPTION = CsPackage.COMPONENT_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__REVIEW = CsPackage.COMPONENT_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__STATUS = CsPackage.COMPONENT_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__FEATURES = CsPackage.COMPONENT_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__TARGET_ELEMENT = CsPackage.COMPONENT_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__SOURCE_ELEMENT = CsPackage.COMPONENT_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__ALLOCATED_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT;

	/**
	 * The feature id for the '<em><b>Allocating Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION__ALLOCATING_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT;

	/**
	 * The number of structural features of the '<em>Logical Actor Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ACTOR_REALIZATION_FEATURE_COUNT = CsPackage.COMPONENT_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalNodeImpl <em>Physical Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalNodeImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalNode()
	 * @generated
	 */
	int PHYSICAL_NODE = 10;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_EXTENSIONS = PHYSICAL_COMPONENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ID = PHYSICAL_COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SID = PHYSICAL_COMPONENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONSTRAINTS = PHYSICAL_COMPONENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_CONSTRAINTS = PHYSICAL_COMPONENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__NAME = PHYSICAL_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ABSTRACT_TYPED_ELEMENTS = PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__INCOMING_TRACES = PHYSICAL_COMPONENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OUTGOING_TRACES = PHYSICAL_COMPONENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__VISIBLE_IN_DOC = PHYSICAL_COMPONENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__VISIBLE_IN_LM = PHYSICAL_COMPONENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUMMARY = PHYSICAL_COMPONENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DESCRIPTION = PHYSICAL_COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__REVIEW = PHYSICAL_COMPONENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PROPERTY_VALUES = PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_ENUMERATION_PROPERTY_TYPES = PHYSICAL_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__APPLIED_PROPERTY_VALUES = PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PROPERTY_VALUE_GROUPS = PHYSICAL_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__APPLIED_PROPERTY_VALUE_GROUPS = PHYSICAL_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__STATUS = PHYSICAL_COMPONENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__FEATURES = PHYSICAL_COMPONENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__APPLIED_REQUIREMENTS = PHYSICAL_COMPONENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_TRACES = PHYSICAL_COMPONENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONTAINED_GENERIC_TRACES = PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONTAINED_REQUIREMENTS_TRACES = PHYSICAL_COMPONENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__NAMING_RULES = PHYSICAL_COMPONENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__TYPED_ELEMENTS = PHYSICAL_COMPONENT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_FUNCTIONAL_ALLOCATION = PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_COMPONENT_EXCHANGES = PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__FUNCTIONAL_ALLOCATIONS = PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ALLOCATED_FUNCTIONS = PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__IN_EXCHANGE_LINKS = PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OUT_EXCHANGE_LINKS = PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_ABSTRACT_CAPABILITY_PKG = PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_INTERFACE_PKG = PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_DATA_PKG = PHYSICAL_COMPONENT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_STATE_MACHINES = PHYSICAL_COMPONENT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ABSTRACT = PHYSICAL_COMPONENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_GENERALIZATIONS = PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUPER_GENERALIZATIONS = PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUB_GENERALIZATIONS = PHYSICAL_COMPONENT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUPER = PHYSICAL_COMPONENT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUB = PHYSICAL_COMPONENT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_FEATURES = PHYSICAL_COMPONENT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONTAINED_PROPERTIES = PHYSICAL_COMPONENT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PARTITIONS = PHYSICAL_COMPONENT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__REPRESENTING_PARTITIONS = PHYSICAL_COMPONENT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_INTERFACE_ALLOCATIONS = PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__PROVISIONED_INTERFACE_ALLOCATIONS = PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ALLOCATED_INTERFACES = PHYSICAL_COMPONENT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_COMMUNICATION_LINKS = PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__PRODUCE = PHYSICAL_COMPONENT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONSUME = PHYSICAL_COMPONENT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SEND = PHYSICAL_COMPONENT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__RECEIVE = PHYSICAL_COMPONENT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CALL = PHYSICAL_COMPONENT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__EXECUTE = PHYSICAL_COMPONENT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__WRITE = PHYSICAL_COMPONENT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ACCESS = PHYSICAL_COMPONENT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ACQUIRE = PHYSICAL_COMPONENT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__TRANSMIT = PHYSICAL_COMPONENT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_INTERFACE_USES = PHYSICAL_COMPONENT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__USED_INTERFACE_LINKS = PHYSICAL_COMPONENT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__USED_INTERFACES = PHYSICAL_COMPONENT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_INTERFACE_IMPLEMENTATIONS = PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__IMPLEMENTED_INTERFACE_LINKS = PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__IMPLEMENTED_INTERFACES = PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__PROVISIONED_COMPONENT_ALLOCATIONS = PHYSICAL_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__PROVISIONING_COMPONENT_ALLOCATIONS = PHYSICAL_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ALLOCATED_COMPONENTS = PHYSICAL_COMPONENT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ALLOCATING_COMPONENTS = PHYSICAL_COMPONENT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__PROVIDED_INTERFACES = PHYSICAL_COMPONENT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__REQUIRED_INTERFACES = PHYSICAL_COMPONENT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONTAINED_COMPONENT_PORTS = PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONTAINED_PARTS = PHYSICAL_COMPONENT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__CONTAINED_PHYSICAL_PORTS = PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PHYSICAL_PATH = PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PHYSICAL_LINKS = PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PHYSICAL_LINK_CATEGORIES = PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__INVOLVING_INVOLVEMENTS = PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Data Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DATA_COMPONENT = PHYSICAL_COMPONENT__DATA_COMPONENT;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DATA_TYPE = PHYSICAL_COMPONENT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = PHYSICAL_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DEPLOYING_LINKS = PHYSICAL_COMPONENT__DEPLOYING_LINKS;

	/**
	 * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DEPLOYMENT_LINKS = PHYSICAL_COMPONENT__DEPLOYMENT_LINKS;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__KIND = PHYSICAL_COMPONENT__KIND;

	/**
	 * The feature id for the '<em><b>Nature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__NATURE = PHYSICAL_COMPONENT__NATURE;

	/**
	 * The feature id for the '<em><b>Owned Deployment Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_DEPLOYMENT_LINKS = PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Deployment Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_DEPLOYMENT_ASPECT = PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT;

	/**
	 * The feature id for the '<em><b>Allocator Configuration Items</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ALLOCATOR_CONFIGURATION_ITEMS = PHYSICAL_COMPONENT__ALLOCATOR_CONFIGURATION_ITEMS;

	/**
	 * The feature id for the '<em><b>Owned Physical Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PHYSICAL_COMPONENTS = PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Component Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_PHYSICAL_COMPONENT_PKGS = PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Logical Component Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__OWNED_LOGICAL_COMPONENT_REALIZATIONS = PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Logical Component Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__LOGICAL_COMPONENT_REALIZATIONS = PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Logical Interface Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__LOGICAL_INTERFACE_REALIZATIONS = PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUB_PHYSICAL_COMPONENTS = PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Realized Logical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__REALIZED_LOGICAL_COMPONENTS = PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocated Physical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__ALLOCATED_PHYSICAL_FUNCTIONS = PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>Deployed Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DEPLOYED_PHYSICAL_COMPONENTS = PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Deploying Physical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DEPLOYING_PHYSICAL_COMPONENTS = PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Deploying Physical Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__DEPLOYING_PHYSICAL_ACTORS = PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS;

	/**
	 * The feature id for the '<em><b>Sub Physical Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE__SUB_PHYSICAL_NODES = PHYSICAL_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Physical Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_NODE_FEATURE_COUNT = PHYSICAL_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalArchitectureRealizationImpl <em>Logical Architecture Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.LogicalArchitectureRealizationImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalArchitectureRealization()
	 * @generated
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION = 11;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__OWNED_EXTENSIONS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__ID = CsPackage.ARCHITECTURE_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__SID = CsPackage.ARCHITECTURE_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__OWNED_CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__REALIZED_FLOW = CsPackage.ARCHITECTURE_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__INCOMING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__OUTGOING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__VISIBLE_IN_DOC = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__VISIBLE_IN_LM = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__SUMMARY = CsPackage.ARCHITECTURE_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__DESCRIPTION = CsPackage.ARCHITECTURE_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__REVIEW = CsPackage.ARCHITECTURE_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__STATUS = CsPackage.ARCHITECTURE_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__FEATURES = CsPackage.ARCHITECTURE_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__TARGET_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__SOURCE_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Architecture</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__ALLOCATED_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE;

	/**
	 * The feature id for the '<em><b>Allocating Architecture</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION__ALLOCATING_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE;

	/**
	 * The number of structural features of the '<em>Logical Architecture Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_ARCHITECTURE_REALIZATION_FEATURE_COUNT = CsPackage.ARCHITECTURE_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalComponentRealizationImpl <em>Logical Component Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.LogicalComponentRealizationImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalComponentRealization()
	 * @generated
	 */
	int LOGICAL_COMPONENT_REALIZATION = 12;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__OWNED_EXTENSIONS = CsPackage.COMPONENT_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__ID = CsPackage.COMPONENT_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__SID = CsPackage.COMPONENT_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__REALIZED_FLOW = CsPackage.COMPONENT_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__INCOMING_TRACES = CsPackage.COMPONENT_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__OUTGOING_TRACES = CsPackage.COMPONENT_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__VISIBLE_IN_DOC = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__VISIBLE_IN_LM = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__SUMMARY = CsPackage.COMPONENT_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__DESCRIPTION = CsPackage.COMPONENT_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__REVIEW = CsPackage.COMPONENT_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__STATUS = CsPackage.COMPONENT_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__FEATURES = CsPackage.COMPONENT_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__TARGET_ELEMENT = CsPackage.COMPONENT_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__SOURCE_ELEMENT = CsPackage.COMPONENT_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__ALLOCATED_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT;

	/**
	 * The feature id for the '<em><b>Allocating Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION__ALLOCATING_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT;

	/**
	 * The number of structural features of the '<em>Logical Component Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_COMPONENT_REALIZATION_FEATURE_COUNT = CsPackage.COMPONENT_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalInterfaceRealizationImpl <em>Logical Interface Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.LogicalInterfaceRealizationImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalInterfaceRealization()
	 * @generated
	 */
	int LOGICAL_INTERFACE_REALIZATION = 13;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__OWNED_EXTENSIONS = CsPackage.INTERFACE_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__ID = CsPackage.INTERFACE_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__SID = CsPackage.INTERFACE_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__CONSTRAINTS = CsPackage.INTERFACE_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__OWNED_CONSTRAINTS = CsPackage.INTERFACE_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__REALIZED_FLOW = CsPackage.INTERFACE_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__INCOMING_TRACES = CsPackage.INTERFACE_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__OUTGOING_TRACES = CsPackage.INTERFACE_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__VISIBLE_IN_DOC = CsPackage.INTERFACE_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__VISIBLE_IN_LM = CsPackage.INTERFACE_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__SUMMARY = CsPackage.INTERFACE_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__DESCRIPTION = CsPackage.INTERFACE_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__REVIEW = CsPackage.INTERFACE_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.INTERFACE_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.INTERFACE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.INTERFACE_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.INTERFACE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.INTERFACE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__STATUS = CsPackage.INTERFACE_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__FEATURES = CsPackage.INTERFACE_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.INTERFACE_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__TARGET_ELEMENT = CsPackage.INTERFACE_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__SOURCE_ELEMENT = CsPackage.INTERFACE_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__ALLOCATED_INTERFACE = CsPackage.INTERFACE_ALLOCATION__ALLOCATED_INTERFACE;

	/**
	 * The feature id for the '<em><b>Allocating Interface Allocator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION__ALLOCATING_INTERFACE_ALLOCATOR = CsPackage.INTERFACE_ALLOCATION__ALLOCATING_INTERFACE_ALLOCATOR;

	/**
	 * The number of structural features of the '<em>Logical Interface Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_INTERFACE_REALIZATION_FEATURE_COUNT = CsPackage.INTERFACE_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalContextImpl <em>Physical Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.impl.PhysicalContextImpl
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalContext()
	 * @generated
	 */
	int PHYSICAL_CONTEXT = 14;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_EXTENSIONS = CsPackage.COMPONENT_CONTEXT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ID = CsPackage.COMPONENT_CONTEXT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SID = CsPackage.COMPONENT_CONTEXT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONSTRAINTS = CsPackage.COMPONENT_CONTEXT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_CONSTRAINTS = CsPackage.COMPONENT_CONTEXT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__NAME = CsPackage.COMPONENT_CONTEXT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT_CONTEXT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__INCOMING_TRACES = CsPackage.COMPONENT_CONTEXT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OUTGOING_TRACES = CsPackage.COMPONENT_CONTEXT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__VISIBLE_IN_DOC = CsPackage.COMPONENT_CONTEXT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__VISIBLE_IN_LM = CsPackage.COMPONENT_CONTEXT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SUMMARY = CsPackage.COMPONENT_CONTEXT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__DESCRIPTION = CsPackage.COMPONENT_CONTEXT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__REVIEW = CsPackage.COMPONENT_CONTEXT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_CONTEXT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_CONTEXT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_CONTEXT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_CONTEXT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_CONTEXT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__STATUS = CsPackage.COMPONENT_CONTEXT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__FEATURES = CsPackage.COMPONENT_CONTEXT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_CONTEXT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_TRACES = CsPackage.COMPONENT_CONTEXT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_CONTEXT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT_CONTEXT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__NAMING_RULES = CsPackage.COMPONENT_CONTEXT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__TYPED_ELEMENTS = CsPackage.COMPONENT_CONTEXT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT_CONTEXT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_CONTEXT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_CONTEXT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT_CONTEXT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__IN_EXCHANGE_LINKS = CsPackage.COMPONENT_CONTEXT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT_CONTEXT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_DATA_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_STATE_MACHINES = CsPackage.COMPONENT_CONTEXT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ABSTRACT = CsPackage.COMPONENT_CONTEXT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SUPER_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SUB_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SUPER = CsPackage.COMPONENT_CONTEXT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SUB = CsPackage.COMPONENT_CONTEXT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_FEATURES = CsPackage.COMPONENT_CONTEXT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONTAINED_PROPERTIES = CsPackage.COMPONENT_CONTEXT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_PARTITIONS = CsPackage.COMPONENT_CONTEXT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__REPRESENTING_PARTITIONS = CsPackage.COMPONENT_CONTEXT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ALLOCATED_INTERFACES = CsPackage.COMPONENT_CONTEXT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT_CONTEXT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__PRODUCE = CsPackage.COMPONENT_CONTEXT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONSUME = CsPackage.COMPONENT_CONTEXT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__SEND = CsPackage.COMPONENT_CONTEXT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__RECEIVE = CsPackage.COMPONENT_CONTEXT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CALL = CsPackage.COMPONENT_CONTEXT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__EXECUTE = CsPackage.COMPONENT_CONTEXT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__WRITE = CsPackage.COMPONENT_CONTEXT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ACCESS = CsPackage.COMPONENT_CONTEXT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ACQUIRE = CsPackage.COMPONENT_CONTEXT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__TRANSMIT = CsPackage.COMPONENT_CONTEXT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_INTERFACE_USES = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__USED_INTERFACE_LINKS = CsPackage.COMPONENT_CONTEXT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__USED_INTERFACES = CsPackage.COMPONENT_CONTEXT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT_CONTEXT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT_CONTEXT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ALLOCATED_COMPONENTS = CsPackage.COMPONENT_CONTEXT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__ALLOCATING_COMPONENTS = CsPackage.COMPONENT_CONTEXT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__PROVIDED_INTERFACES = CsPackage.COMPONENT_CONTEXT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__REQUIRED_INTERFACES = CsPackage.COMPONENT_CONTEXT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONTAINED_PARTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The number of structural features of the '<em>Physical Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_CONTEXT_FEATURE_COUNT = CsPackage.COMPONENT_CONTEXT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.PhysicalComponentKind <em>Physical Component Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentKind
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponentKind()
	 * @generated
	 */
	int PHYSICAL_COMPONENT_KIND = 15;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.pa.PhysicalComponentNature <em>Physical Component Nature</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentNature
	 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponentNature()
	 * @generated
	 */
	int PHYSICAL_COMPONENT_NATURE = 16;


	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg <em>Physical Architecture Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Architecture Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg
	 * @generated
	 */
	EClass getPhysicalArchitecturePkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg#getOwnedPhysicalArchitecturePkgs <em>Owned Physical Architecture Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Architecture Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg#getOwnedPhysicalArchitecturePkgs()
	 * @see #getPhysicalArchitecturePkg()
	 * @generated
	 */
	EReference getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg#getOwnedPhysicalArchitectures <em>Owned Physical Architectures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Architectures</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg#getOwnedPhysicalArchitectures()
	 * @see #getPhysicalArchitecturePkg()
	 * @generated
	 */
	EReference getPhysicalArchitecturePkg_OwnedPhysicalArchitectures();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture <em>Physical Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Architecture</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture
	 * @generated
	 */
	EClass getPhysicalArchitecture();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalContext <em>Owned Physical Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Physical Context</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalContext()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_OwnedPhysicalContext();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalComponent <em>Owned Physical Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Physical Component</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalComponent()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_OwnedPhysicalComponent();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalComponentPkg <em>Owned Physical Component Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Physical Component Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalComponentPkg()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_OwnedPhysicalComponentPkg();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalActorPkg <em>Owned Physical Actor Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Physical Actor Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalActorPkg()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_OwnedPhysicalActorPkg();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contained Capability Realization Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getContainedCapabilityRealizationPkg()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_ContainedCapabilityRealizationPkg();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getContainedPhysicalFunctionPkg <em>Contained Physical Function Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contained Physical Function Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getContainedPhysicalFunctionPkg()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_ContainedPhysicalFunctionPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedDeployments <em>Owned Deployments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Deployments</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedDeployments()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_OwnedDeployments();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedLogicalArchitectureRealizations <em>Owned Logical Architecture Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Logical Architecture Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedLogicalArchitectureRealizations()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_OwnedLogicalArchitectureRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectureRealizations <em>Allocated Logical Architecture Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Logical Architecture Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectureRealizations()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectures <em>Allocated Logical Architectures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Logical Architectures</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectures()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_AllocatedLogicalArchitectures();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatingEpbsArchitectures <em>Allocating Epbs Architectures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocating Epbs Architectures</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatingEpbsArchitectures()
	 * @see #getPhysicalArchitecture()
	 * @generated
	 */
	EReference getPhysicalArchitecture_AllocatingEpbsArchitectures();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalFunction <em>Physical Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Function</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction
	 * @generated
	 */
	EClass getPhysicalFunction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getOwnedPhysicalFunctionPkgs <em>Owned Physical Function Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Function Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getOwnedPhysicalFunctionPkgs()
	 * @see #getPhysicalFunction()
	 * @generated
	 */
	EReference getPhysicalFunction_OwnedPhysicalFunctionPkgs();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalActors <em>Allocator Physical Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocator Physical Actors</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalActors()
	 * @see #getPhysicalFunction()
	 * @generated
	 */
	EReference getPhysicalFunction_AllocatorPhysicalActors();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalComponents <em>Allocator Physical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocator Physical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalComponents()
	 * @see #getPhysicalFunction()
	 * @generated
	 */
	EReference getPhysicalFunction_AllocatorPhysicalComponents();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getRealizedLogicalFunctions <em>Realized Logical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Logical Functions</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getRealizedLogicalFunctions()
	 * @see #getPhysicalFunction()
	 * @generated
	 */
	EReference getPhysicalFunction_RealizedLogicalFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getContainedPhysicalFunctions <em>Contained Physical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contained Physical Functions</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getContainedPhysicalFunctions()
	 * @see #getPhysicalFunction()
	 * @generated
	 */
	EReference getPhysicalFunction_ContainedPhysicalFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getChildrenPhysicalFunctions <em>Children Physical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children Physical Functions</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getChildrenPhysicalFunctions()
	 * @see #getPhysicalFunction()
	 * @generated
	 */
	EReference getPhysicalFunction_ChildrenPhysicalFunctions();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg <em>Physical Function Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Function Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunctionPkg
	 * @generated
	 */
	EClass getPhysicalFunctionPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg#getOwnedPhysicalFunctions <em>Owned Physical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Functions</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunctionPkg#getOwnedPhysicalFunctions()
	 * @see #getPhysicalFunctionPkg()
	 * @generated
	 */
	EReference getPhysicalFunctionPkg_OwnedPhysicalFunctions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg#getOwnedPhysicalFunctionPkgs <em>Owned Physical Function Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Function Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunctionPkg#getOwnedPhysicalFunctionPkgs()
	 * @see #getPhysicalFunctionPkg()
	 * @generated
	 */
	EReference getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.AbstractPhysicalComponent <em>Abstract Physical Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Physical Component</em>'.
	 * @see org.polarsys.capella.core.data.pa.AbstractPhysicalComponent
	 * @generated
	 */
	EClass getAbstractPhysicalComponent();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getKind()
	 * @see #getAbstractPhysicalComponent()
	 * @generated
	 */
	EAttribute getAbstractPhysicalComponent_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getNature <em>Nature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nature</em>'.
	 * @see org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getNature()
	 * @see #getAbstractPhysicalComponent()
	 * @generated
	 */
	EAttribute getAbstractPhysicalComponent_Nature();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Deployment Links</em>'.
	 * @see org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getOwnedDeploymentLinks()
	 * @see #getAbstractPhysicalComponent()
	 * @generated
	 */
	EReference getAbstractPhysicalComponent_OwnedDeploymentLinks();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getOwnedDeploymentAspect <em>Owned Deployment Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Deployment Aspect</em>'.
	 * @see org.polarsys.capella.core.data.pa.AbstractPhysicalComponent#getOwnedDeploymentAspect()
	 * @see #getAbstractPhysicalComponent()
	 * @generated
	 */
	EReference getAbstractPhysicalComponent_OwnedDeploymentAspect();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalComponent <em>Physical Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Component</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent
	 * @generated
	 */
	EClass getPhysicalComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponents <em>Owned Physical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponents()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_OwnedPhysicalComponents();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Component Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponentPkgs()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_OwnedPhysicalComponentPkgs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedLogicalComponentRealizations <em>Owned Logical Component Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Logical Component Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedLogicalComponentRealizations()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_OwnedLogicalComponentRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalComponentRealizations <em>Logical Component Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Component Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalComponentRealizations()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_LogicalComponentRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalInterfaceRealizations <em>Logical Interface Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Interface Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalInterfaceRealizations()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_LogicalInterfaceRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getSubPhysicalComponents <em>Sub Physical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Physical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getSubPhysicalComponents()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_SubPhysicalComponents();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getRealizedLogicalComponents <em>Realized Logical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Logical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getRealizedLogicalComponents()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_RealizedLogicalComponents();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Physical Functions</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getAllocatedPhysicalFunctions()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_AllocatedPhysicalFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deployed Physical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployedPhysicalComponents()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_DeployedPhysicalComponents();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalComponents <em>Deploying Physical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deploying Physical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalComponents()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_DeployingPhysicalComponents();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalActors <em>Deploying Physical Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deploying Physical Actors</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalActors()
	 * @see #getPhysicalComponent()
	 * @generated
	 */
	EReference getPhysicalComponent_DeployingPhysicalActors();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalActorPkg <em>Physical Actor Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Actor Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActorPkg
	 * @generated
	 */
	EClass getPhysicalActorPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActorPkg#getOwnedPhysicalActorPkgs <em>Owned Physical Actor Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Actor Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActorPkg#getOwnedPhysicalActorPkgs()
	 * @see #getPhysicalActorPkg()
	 * @generated
	 */
	EReference getPhysicalActorPkg_OwnedPhysicalActorPkgs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActorPkg#getOwnedPhysicalActors <em>Owned Physical Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Actors</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActorPkg#getOwnedPhysicalActors()
	 * @see #getPhysicalActorPkg()
	 * @generated
	 */
	EReference getPhysicalActorPkg_OwnedPhysicalActors();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalComponentPkg <em>Physical Component Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Component Pkg</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentPkg
	 * @generated
	 */
	EClass getPhysicalComponentPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedComponents <em>Owned Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedComponents()
	 * @see #getPhysicalComponentPkg()
	 * @generated
	 */
	EReference getPhysicalComponentPkg_OwnedComponents();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Physical Component Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedPhysicalComponentPkgs()
	 * @see #getPhysicalComponentPkg()
	 * @generated
	 */
	EReference getPhysicalComponentPkg_OwnedPhysicalComponentPkgs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedKeyParts <em>Owned Key Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Key Parts</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedKeyParts()
	 * @see #getPhysicalComponentPkg()
	 * @generated
	 */
	EReference getPhysicalComponentPkg_OwnedKeyParts();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedDeployments <em>Owned Deployments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Deployments</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentPkg#getOwnedDeployments()
	 * @see #getPhysicalComponentPkg()
	 * @generated
	 */
	EReference getPhysicalComponentPkg_OwnedDeployments();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalActor <em>Physical Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Actor</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor
	 * @generated
	 */
	EClass getPhysicalActor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActor#getOwnedLogicalActorRealizations <em>Owned Logical Actor Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Logical Actor Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor#getOwnedLogicalActorRealizations()
	 * @see #getPhysicalActor()
	 * @generated
	 */
	EReference getPhysicalActor_OwnedLogicalActorRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActor#getLogicalActorRealizations <em>Logical Actor Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Actor Realizations</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor#getLogicalActorRealizations()
	 * @see #getPhysicalActor()
	 * @generated
	 */
	EReference getPhysicalActor_LogicalActorRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActor#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Physical Functions</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor#getAllocatedPhysicalFunctions()
	 * @see #getPhysicalActor()
	 * @generated
	 */
	EReference getPhysicalActor_AllocatedPhysicalFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActor#getRealizedLogicalActors <em>Realized Logical Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Logical Actors</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor#getRealizedLogicalActors()
	 * @see #getPhysicalActor()
	 * @generated
	 */
	EReference getPhysicalActor_RealizedLogicalActors();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalActor#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deployed Physical Components</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor#getDeployedPhysicalComponents()
	 * @see #getPhysicalActor()
	 * @generated
	 */
	EReference getPhysicalActor_DeployedPhysicalComponents();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.LogicalActorRealization <em>Logical Actor Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Actor Realization</em>'.
	 * @see org.polarsys.capella.core.data.pa.LogicalActorRealization
	 * @generated
	 */
	EClass getLogicalActorRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalNode <em>Physical Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Node</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalNode
	 * @generated
	 */
	EClass getPhysicalNode();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.PhysicalNode#getSubPhysicalNodes <em>Sub Physical Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Physical Nodes</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalNode#getSubPhysicalNodes()
	 * @see #getPhysicalNode()
	 * @generated
	 */
	EReference getPhysicalNode_SubPhysicalNodes();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.LogicalArchitectureRealization <em>Logical Architecture Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Architecture Realization</em>'.
	 * @see org.polarsys.capella.core.data.pa.LogicalArchitectureRealization
	 * @generated
	 */
	EClass getLogicalArchitectureRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.LogicalComponentRealization <em>Logical Component Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Component Realization</em>'.
	 * @see org.polarsys.capella.core.data.pa.LogicalComponentRealization
	 * @generated
	 */
	EClass getLogicalComponentRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.LogicalInterfaceRealization <em>Logical Interface Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Interface Realization</em>'.
	 * @see org.polarsys.capella.core.data.pa.LogicalInterfaceRealization
	 * @generated
	 */
	EClass getLogicalInterfaceRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.PhysicalContext <em>Physical Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Physical Context</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalContext
	 * @generated
	 */
	EClass getPhysicalContext();

	/**
	 * Returns the meta object for enum '{@link org.polarsys.capella.core.data.pa.PhysicalComponentKind <em>Physical Component Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Physical Component Kind</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentKind
	 * @generated
	 */
	EEnum getPhysicalComponentKind();

	/**
	 * Returns the meta object for enum '{@link org.polarsys.capella.core.data.pa.PhysicalComponentNature <em>Physical Component Nature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Physical Component Nature</em>'.
	 * @see org.polarsys.capella.core.data.pa.PhysicalComponentNature
	 * @generated
	 */
	EEnum getPhysicalComponentNature();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PaFactory getPaFactory();

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
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitecturePkgImpl <em>Physical Architecture Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalArchitecturePkgImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalArchitecturePkg()
		 * @generated
		 */
		EClass PHYSICAL_ARCHITECTURE_PKG = eINSTANCE.getPhysicalArchitecturePkg();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Architecture Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS = eINSTANCE.getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Architectures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES = eINSTANCE.getPhysicalArchitecturePkg_OwnedPhysicalArchitectures();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl <em>Physical Architecture</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalArchitecture()
		 * @generated
		 */
		EClass PHYSICAL_ARCHITECTURE = eINSTANCE.getPhysicalArchitecture();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT = eINSTANCE.getPhysicalArchitecture_OwnedPhysicalContext();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT = eINSTANCE.getPhysicalArchitecture_OwnedPhysicalComponent();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Component Pkg</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG = eINSTANCE.getPhysicalArchitecture_OwnedPhysicalComponentPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Actor Pkg</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG = eINSTANCE.getPhysicalArchitecture_OwnedPhysicalActorPkg();

		/**
		 * The meta object literal for the '<em><b>Contained Capability Realization Pkg</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = eINSTANCE.getPhysicalArchitecture_ContainedCapabilityRealizationPkg();

		/**
		 * The meta object literal for the '<em><b>Contained Physical Function Pkg</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG = eINSTANCE.getPhysicalArchitecture_ContainedPhysicalFunctionPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Deployments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS = eINSTANCE.getPhysicalArchitecture_OwnedDeployments();

		/**
		 * The meta object literal for the '<em><b>Owned Logical Architecture Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS = eINSTANCE.getPhysicalArchitecture_OwnedLogicalArchitectureRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated Logical Architecture Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS = eINSTANCE.getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated Logical Architectures</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES = eINSTANCE.getPhysicalArchitecture_AllocatedLogicalArchitectures();

		/**
		 * The meta object literal for the '<em><b>Allocating Epbs Architectures</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES = eINSTANCE.getPhysicalArchitecture_AllocatingEpbsArchitectures();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl <em>Physical Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalFunction()
		 * @generated
		 */
		EClass PHYSICAL_FUNCTION = eINSTANCE.getPhysicalFunction();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Function Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS = eINSTANCE.getPhysicalFunction_OwnedPhysicalFunctionPkgs();

		/**
		 * The meta object literal for the '<em><b>Allocator Physical Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION__ALLOCATOR_PHYSICAL_ACTORS = eINSTANCE.getPhysicalFunction_AllocatorPhysicalActors();

		/**
		 * The meta object literal for the '<em><b>Allocator Physical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION__ALLOCATOR_PHYSICAL_COMPONENTS = eINSTANCE.getPhysicalFunction_AllocatorPhysicalComponents();

		/**
		 * The meta object literal for the '<em><b>Realized Logical Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS = eINSTANCE.getPhysicalFunction_RealizedLogicalFunctions();

		/**
		 * The meta object literal for the '<em><b>Contained Physical Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS = eINSTANCE.getPhysicalFunction_ContainedPhysicalFunctions();

		/**
		 * The meta object literal for the '<em><b>Children Physical Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS = eINSTANCE.getPhysicalFunction_ChildrenPhysicalFunctions();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionPkgImpl <em>Physical Function Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalFunctionPkgImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalFunctionPkg()
		 * @generated
		 */
		EClass PHYSICAL_FUNCTION_PKG = eINSTANCE.getPhysicalFunctionPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Functions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS = eINSTANCE.getPhysicalFunctionPkg_OwnedPhysicalFunctions();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Function Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTION_PKGS = eINSTANCE.getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl <em>Abstract Physical Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.AbstractPhysicalComponentImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getAbstractPhysicalComponent()
		 * @generated
		 */
		EClass ABSTRACT_PHYSICAL_COMPONENT = eINSTANCE.getAbstractPhysicalComponent();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_PHYSICAL_COMPONENT__KIND = eINSTANCE.getAbstractPhysicalComponent_Kind();

		/**
		 * The meta object literal for the '<em><b>Nature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_PHYSICAL_COMPONENT__NATURE = eINSTANCE.getAbstractPhysicalComponent_Nature();

		/**
		 * The meta object literal for the '<em><b>Owned Deployment Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS = eINSTANCE.getAbstractPhysicalComponent_OwnedDeploymentLinks();

		/**
		 * The meta object literal for the '<em><b>Owned Deployment Aspect</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_ASPECT = eINSTANCE.getAbstractPhysicalComponent_OwnedDeploymentAspect();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl <em>Physical Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponent()
		 * @generated
		 */
		EClass PHYSICAL_COMPONENT = eINSTANCE.getPhysicalComponent();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS = eINSTANCE.getPhysicalComponent_OwnedPhysicalComponents();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Component Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS = eINSTANCE.getPhysicalComponent_OwnedPhysicalComponentPkgs();

		/**
		 * The meta object literal for the '<em><b>Owned Logical Component Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS = eINSTANCE.getPhysicalComponent_OwnedLogicalComponentRealizations();

		/**
		 * The meta object literal for the '<em><b>Logical Component Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS = eINSTANCE.getPhysicalComponent_LogicalComponentRealizations();

		/**
		 * The meta object literal for the '<em><b>Logical Interface Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS = eINSTANCE.getPhysicalComponent_LogicalInterfaceRealizations();

		/**
		 * The meta object literal for the '<em><b>Sub Physical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS = eINSTANCE.getPhysicalComponent_SubPhysicalComponents();

		/**
		 * The meta object literal for the '<em><b>Realized Logical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS = eINSTANCE.getPhysicalComponent_RealizedLogicalComponents();

		/**
		 * The meta object literal for the '<em><b>Allocated Physical Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS = eINSTANCE.getPhysicalComponent_AllocatedPhysicalFunctions();

		/**
		 * The meta object literal for the '<em><b>Deployed Physical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS = eINSTANCE.getPhysicalComponent_DeployedPhysicalComponents();

		/**
		 * The meta object literal for the '<em><b>Deploying Physical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS = eINSTANCE.getPhysicalComponent_DeployingPhysicalComponents();

		/**
		 * The meta object literal for the '<em><b>Deploying Physical Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS = eINSTANCE.getPhysicalComponent_DeployingPhysicalActors();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorPkgImpl <em>Physical Actor Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalActorPkgImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalActorPkg()
		 * @generated
		 */
		EClass PHYSICAL_ACTOR_PKG = eINSTANCE.getPhysicalActorPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Actor Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS = eINSTANCE.getPhysicalActorPkg_OwnedPhysicalActorPkgs();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Actors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS = eINSTANCE.getPhysicalActorPkg_OwnedPhysicalActors();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl <em>Physical Component Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalComponentPkgImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponentPkg()
		 * @generated
		 */
		EClass PHYSICAL_COMPONENT_PKG = eINSTANCE.getPhysicalComponentPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT_PKG__OWNED_COMPONENTS = eINSTANCE.getPhysicalComponentPkg_OwnedComponents();

		/**
		 * The meta object literal for the '<em><b>Owned Physical Component Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS = eINSTANCE.getPhysicalComponentPkg_OwnedPhysicalComponentPkgs();

		/**
		 * The meta object literal for the '<em><b>Owned Key Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS = eINSTANCE.getPhysicalComponentPkg_OwnedKeyParts();

		/**
		 * The meta object literal for the '<em><b>Owned Deployments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS = eINSTANCE.getPhysicalComponentPkg_OwnedDeployments();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl <em>Physical Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalActor()
		 * @generated
		 */
		EClass PHYSICAL_ACTOR = eINSTANCE.getPhysicalActor();

		/**
		 * The meta object literal for the '<em><b>Owned Logical Actor Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS = eINSTANCE.getPhysicalActor_OwnedLogicalActorRealizations();

		/**
		 * The meta object literal for the '<em><b>Logical Actor Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS = eINSTANCE.getPhysicalActor_LogicalActorRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated Physical Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS = eINSTANCE.getPhysicalActor_AllocatedPhysicalFunctions();

		/**
		 * The meta object literal for the '<em><b>Realized Logical Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS = eINSTANCE.getPhysicalActor_RealizedLogicalActors();

		/**
		 * The meta object literal for the '<em><b>Deployed Physical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS = eINSTANCE.getPhysicalActor_DeployedPhysicalComponents();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalActorRealizationImpl <em>Logical Actor Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.LogicalActorRealizationImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalActorRealization()
		 * @generated
		 */
		EClass LOGICAL_ACTOR_REALIZATION = eINSTANCE.getLogicalActorRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalNodeImpl <em>Physical Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalNodeImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalNode()
		 * @generated
		 */
		EClass PHYSICAL_NODE = eINSTANCE.getPhysicalNode();

		/**
		 * The meta object literal for the '<em><b>Sub Physical Nodes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHYSICAL_NODE__SUB_PHYSICAL_NODES = eINSTANCE.getPhysicalNode_SubPhysicalNodes();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalArchitectureRealizationImpl <em>Logical Architecture Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.LogicalArchitectureRealizationImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalArchitectureRealization()
		 * @generated
		 */
		EClass LOGICAL_ARCHITECTURE_REALIZATION = eINSTANCE.getLogicalArchitectureRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalComponentRealizationImpl <em>Logical Component Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.LogicalComponentRealizationImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalComponentRealization()
		 * @generated
		 */
		EClass LOGICAL_COMPONENT_REALIZATION = eINSTANCE.getLogicalComponentRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.LogicalInterfaceRealizationImpl <em>Logical Interface Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.LogicalInterfaceRealizationImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getLogicalInterfaceRealization()
		 * @generated
		 */
		EClass LOGICAL_INTERFACE_REALIZATION = eINSTANCE.getLogicalInterfaceRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.impl.PhysicalContextImpl <em>Physical Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.impl.PhysicalContextImpl
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalContext()
		 * @generated
		 */
		EClass PHYSICAL_CONTEXT = eINSTANCE.getPhysicalContext();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.PhysicalComponentKind <em>Physical Component Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.PhysicalComponentKind
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponentKind()
		 * @generated
		 */
		EEnum PHYSICAL_COMPONENT_KIND = eINSTANCE.getPhysicalComponentKind();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.PhysicalComponentNature <em>Physical Component Nature</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.pa.PhysicalComponentNature
		 * @see org.polarsys.capella.core.data.pa.impl.PaPackageImpl#getPhysicalComponentNature()
		 * @generated
		 */
		EEnum PHYSICAL_COMPONENT_NATURE = eINSTANCE.getPhysicalComponentNature();

	}

} //PaPackage
