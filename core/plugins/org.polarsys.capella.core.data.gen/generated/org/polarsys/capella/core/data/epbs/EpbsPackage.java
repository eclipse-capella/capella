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
package org.polarsys.capella.core.data.epbs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
 *        annotation="http://www.polarsys.org/kitalpha/dsl/2007/dslfactory trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(E)PBS (for (End-)Product Breakdown Structure) aims at defining the system\'s work product breakdown (close to Clearcase/UCM\'s components concept).\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='epbs' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
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
	String eNS_URI = "http://www.polarsys.org/capella/core/epbs/1.0.0"; //$NON-NLS-1$

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
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE_PKG__APPLIED_REQUIREMENTS = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_REQUIREMENTS;

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
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE_PKG__CONTAINED_REQUIREMENTS_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__CONTAINED_REQUIREMENTS_TRACES;

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
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_REQUIREMENTS;

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
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_REQUIREMENTS_TRACES;

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
	 * The feature id for the '<em><b>Owned Requirement Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__OWNED_REQUIREMENT_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_REQUIREMENT_PKGS;

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
	 * The feature id for the '<em><b>Owned EPBS Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Configuration Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Configuration Item Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contained Capability Realization Pkg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Owned Physical Architecture Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Allocated Physical Architecture Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Allocated Physical Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>EPBS Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_ARCHITECTURE_FEATURE_COUNT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.EPBSContextImpl <em>EPBS Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.epbs.impl.EPBSContextImpl
	 * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getEPBSContext()
	 * @generated
	 */
	int EPBS_CONTEXT = 2;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_EXTENSIONS = CsPackage.COMPONENT_CONTEXT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ID = CsPackage.COMPONENT_CONTEXT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SID = CsPackage.COMPONENT_CONTEXT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONSTRAINTS = CsPackage.COMPONENT_CONTEXT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_CONSTRAINTS = CsPackage.COMPONENT_CONTEXT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__NAME = CsPackage.COMPONENT_CONTEXT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT_CONTEXT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__INCOMING_TRACES = CsPackage.COMPONENT_CONTEXT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OUTGOING_TRACES = CsPackage.COMPONENT_CONTEXT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__VISIBLE_IN_DOC = CsPackage.COMPONENT_CONTEXT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__VISIBLE_IN_LM = CsPackage.COMPONENT_CONTEXT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SUMMARY = CsPackage.COMPONENT_CONTEXT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__DESCRIPTION = CsPackage.COMPONENT_CONTEXT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__REVIEW = CsPackage.COMPONENT_CONTEXT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_CONTEXT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_CONTEXT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_CONTEXT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_CONTEXT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_CONTEXT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__STATUS = CsPackage.COMPONENT_CONTEXT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__FEATURES = CsPackage.COMPONENT_CONTEXT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_CONTEXT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_TRACES = CsPackage.COMPONENT_CONTEXT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_CONTEXT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT_CONTEXT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__NAMING_RULES = CsPackage.COMPONENT_CONTEXT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__TYPED_ELEMENTS = CsPackage.COMPONENT_CONTEXT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT_CONTEXT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_CONTEXT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_CONTEXT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT_CONTEXT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__IN_EXCHANGE_LINKS = CsPackage.COMPONENT_CONTEXT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT_CONTEXT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_DATA_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_STATE_MACHINES = CsPackage.COMPONENT_CONTEXT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ABSTRACT = CsPackage.COMPONENT_CONTEXT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SUPER_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SUB_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SUPER = CsPackage.COMPONENT_CONTEXT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SUB = CsPackage.COMPONENT_CONTEXT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_FEATURES = CsPackage.COMPONENT_CONTEXT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONTAINED_PROPERTIES = CsPackage.COMPONENT_CONTEXT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_PARTITIONS = CsPackage.COMPONENT_CONTEXT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__REPRESENTING_PARTITIONS = CsPackage.COMPONENT_CONTEXT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ALLOCATED_INTERFACES = CsPackage.COMPONENT_CONTEXT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT_CONTEXT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__PRODUCE = CsPackage.COMPONENT_CONTEXT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONSUME = CsPackage.COMPONENT_CONTEXT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__SEND = CsPackage.COMPONENT_CONTEXT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__RECEIVE = CsPackage.COMPONENT_CONTEXT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CALL = CsPackage.COMPONENT_CONTEXT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__EXECUTE = CsPackage.COMPONENT_CONTEXT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__WRITE = CsPackage.COMPONENT_CONTEXT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ACCESS = CsPackage.COMPONENT_CONTEXT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ACQUIRE = CsPackage.COMPONENT_CONTEXT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__TRANSMIT = CsPackage.COMPONENT_CONTEXT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_INTERFACE_USES = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__USED_INTERFACE_LINKS = CsPackage.COMPONENT_CONTEXT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__USED_INTERFACES = CsPackage.COMPONENT_CONTEXT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT_CONTEXT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT_CONTEXT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ALLOCATED_COMPONENTS = CsPackage.COMPONENT_CONTEXT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__ALLOCATING_COMPONENTS = CsPackage.COMPONENT_CONTEXT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__PROVIDED_INTERFACES = CsPackage.COMPONENT_CONTEXT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__REQUIRED_INTERFACES = CsPackage.COMPONENT_CONTEXT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONTAINED_PARTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The number of structural features of the '<em>EPBS Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPBS_CONTEXT_FEATURE_COUNT = CsPackage.COMPONENT_CONTEXT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl <em>Configuration Item Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl
	 * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItemPkg()
	 * @generated
	 */
	int CONFIGURATION_ITEM_PKG = 3;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__APPLIED_REQUIREMENTS = CapellacorePackage.STRUCTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Configuration Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Configuration Item Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemImpl <em>Configuration Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.epbs.impl.ConfigurationItemImpl
	 * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItem()
	 * @generated
	 */
	int CONFIGURATION_ITEM = 4;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_EXTENSIONS = CsPackage.SYSTEM_COMPONENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ID = CsPackage.SYSTEM_COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SID = CsPackage.SYSTEM_COMPONENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONSTRAINTS = CsPackage.SYSTEM_COMPONENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_CONSTRAINTS = CsPackage.SYSTEM_COMPONENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__NAME = CsPackage.SYSTEM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ABSTRACT_TYPED_ELEMENTS = CsPackage.SYSTEM_COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__INCOMING_TRACES = CsPackage.SYSTEM_COMPONENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OUTGOING_TRACES = CsPackage.SYSTEM_COMPONENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__VISIBLE_IN_DOC = CsPackage.SYSTEM_COMPONENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__VISIBLE_IN_LM = CsPackage.SYSTEM_COMPONENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SUMMARY = CsPackage.SYSTEM_COMPONENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__DESCRIPTION = CsPackage.SYSTEM_COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__REVIEW = CsPackage.SYSTEM_COMPONENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PROPERTY_VALUES = CsPackage.SYSTEM_COMPONENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.SYSTEM_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__APPLIED_PROPERTY_VALUES = CsPackage.SYSTEM_COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.SYSTEM_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.SYSTEM_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__STATUS = CsPackage.SYSTEM_COMPONENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__FEATURES = CsPackage.SYSTEM_COMPONENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__APPLIED_REQUIREMENTS = CsPackage.SYSTEM_COMPONENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_TRACES = CsPackage.SYSTEM_COMPONENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONTAINED_GENERIC_TRACES = CsPackage.SYSTEM_COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONTAINED_REQUIREMENTS_TRACES = CsPackage.SYSTEM_COMPONENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__NAMING_RULES = CsPackage.SYSTEM_COMPONENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__TYPED_ELEMENTS = CsPackage.SYSTEM_COMPONENT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.SYSTEM_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_COMPONENT_EXCHANGES = CsPackage.SYSTEM_COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.SYSTEM_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__FUNCTIONAL_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ALLOCATED_FUNCTIONS = CsPackage.SYSTEM_COMPONENT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__IN_EXCHANGE_LINKS = CsPackage.SYSTEM_COMPONENT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OUT_EXCHANGE_LINKS = CsPackage.SYSTEM_COMPONENT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.SYSTEM_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_PKG = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_DATA_PKG = CsPackage.SYSTEM_COMPONENT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_STATE_MACHINES = CsPackage.SYSTEM_COMPONENT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ABSTRACT = CsPackage.SYSTEM_COMPONENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_GENERALIZATIONS = CsPackage.SYSTEM_COMPONENT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SUPER_GENERALIZATIONS = CsPackage.SYSTEM_COMPONENT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SUB_GENERALIZATIONS = CsPackage.SYSTEM_COMPONENT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SUPER = CsPackage.SYSTEM_COMPONENT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SUB = CsPackage.SYSTEM_COMPONENT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_FEATURES = CsPackage.SYSTEM_COMPONENT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONTAINED_PROPERTIES = CsPackage.SYSTEM_COMPONENT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PARTITIONS = CsPackage.SYSTEM_COMPONENT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__REPRESENTING_PARTITIONS = CsPackage.SYSTEM_COMPONENT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ALLOCATED_INTERFACES = CsPackage.SYSTEM_COMPONENT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_COMMUNICATION_LINKS = CsPackage.SYSTEM_COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__PRODUCE = CsPackage.SYSTEM_COMPONENT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONSUME = CsPackage.SYSTEM_COMPONENT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__SEND = CsPackage.SYSTEM_COMPONENT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__RECEIVE = CsPackage.SYSTEM_COMPONENT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CALL = CsPackage.SYSTEM_COMPONENT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__EXECUTE = CsPackage.SYSTEM_COMPONENT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__WRITE = CsPackage.SYSTEM_COMPONENT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ACCESS = CsPackage.SYSTEM_COMPONENT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ACQUIRE = CsPackage.SYSTEM_COMPONENT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__TRANSMIT = CsPackage.SYSTEM_COMPONENT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_USES = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__USED_INTERFACE_LINKS = CsPackage.SYSTEM_COMPONENT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__USED_INTERFACES = CsPackage.SYSTEM_COMPONENT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.SYSTEM_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__IMPLEMENTED_INTERFACE_LINKS = CsPackage.SYSTEM_COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__IMPLEMENTED_INTERFACES = CsPackage.SYSTEM_COMPONENT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.SYSTEM_COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ALLOCATED_COMPONENTS = CsPackage.SYSTEM_COMPONENT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ALLOCATING_COMPONENTS = CsPackage.SYSTEM_COMPONENT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__PROVIDED_INTERFACES = CsPackage.SYSTEM_COMPONENT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__REQUIRED_INTERFACES = CsPackage.SYSTEM_COMPONENT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONTAINED_COMPONENT_PORTS = CsPackage.SYSTEM_COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONTAINED_PARTS = CsPackage.SYSTEM_COMPONENT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__CONTAINED_PHYSICAL_PORTS = CsPackage.SYSTEM_COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_PATH = CsPackage.SYSTEM_COMPONENT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_LINKS = CsPackage.SYSTEM_COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.SYSTEM_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__INVOLVING_INVOLVEMENTS = CsPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = CsPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Data Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__DATA_COMPONENT = CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__DATA_TYPE = CsPackage.SYSTEM_COMPONENT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = CsPackage.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Item Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ITEM_IDENTIFIER = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__KIND = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Configuration Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEMS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEM_PKGS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Owned Physical Artifact Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Allocated Physical Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM__ALLOCATED_PHYSICAL_ARTIFACTS = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Configuration Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_ITEM_FEATURE_COUNT = CsPackage.SYSTEM_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArchitectureRealizationImpl <em>Physical Architecture Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.epbs.impl.PhysicalArchitectureRealizationImpl
	 * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getPhysicalArchitectureRealization()
	 * @generated
	 */
	int PHYSICAL_ARCHITECTURE_REALIZATION = 5;

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
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARCHITECTURE_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_REQUIREMENTS;

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
	int PHYSICAL_ARTIFACT_REALIZATION = 6;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_EXTENSIONS = CsPackage.COMPONENT_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__ID = CsPackage.COMPONENT_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__SID = CsPackage.COMPONENT_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__REALIZED_FLOW = CsPackage.COMPONENT_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__INCOMING_TRACES = CsPackage.COMPONENT_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__OUTGOING_TRACES = CsPackage.COMPONENT_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__VISIBLE_IN_DOC = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__VISIBLE_IN_LM = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__SUMMARY = CsPackage.COMPONENT_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__DESCRIPTION = CsPackage.COMPONENT_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__REVIEW = CsPackage.COMPONENT_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__STATUS = CsPackage.COMPONENT_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__FEATURES = CsPackage.COMPONENT_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__TARGET_ELEMENT = CsPackage.COMPONENT_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__SOURCE_ELEMENT = CsPackage.COMPONENT_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__ALLOCATED_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT;

	/**
	 * The feature id for the '<em><b>Allocating Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION__ALLOCATING_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT;

	/**
	 * The number of structural features of the '<em>Physical Artifact Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHYSICAL_ARTIFACT_REALIZATION_FEATURE_COUNT = CsPackage.COMPONENT_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemKind <em>Configuration Item Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.epbs.ConfigurationItemKind
	 * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItemKind()
	 * @generated
	 */
	int CONFIGURATION_ITEM_KIND = 7;


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
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedEPBSContext <em>Owned EPBS Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned EPBS Context</em>'.
	 * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedEPBSContext()
	 * @see #getEPBSArchitecture()
	 * @generated
	 */
	EReference getEPBSArchitecture_OwnedEPBSContext();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedConfigurationItem <em>Owned Configuration Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Configuration Item</em>'.
	 * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedConfigurationItem()
	 * @see #getEPBSArchitecture()
	 * @generated
	 */
	EReference getEPBSArchitecture_OwnedConfigurationItem();

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
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.EPBSContext <em>EPBS Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EPBS Context</em>'.
	 * @see org.polarsys.capella.core.data.epbs.EPBSContext
	 * @generated
	 */
	EClass getEPBSContext();

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
		 * The meta object literal for the '<em><b>Owned EPBS Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT = eINSTANCE.getEPBSArchitecture_OwnedEPBSContext();

		/**
		 * The meta object literal for the '<em><b>Owned Configuration Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM = eINSTANCE.getEPBSArchitecture_OwnedConfigurationItem();

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
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.EPBSContextImpl <em>EPBS Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.epbs.impl.EPBSContextImpl
		 * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getEPBSContext()
		 * @generated
		 */
		EClass EPBS_CONTEXT = eINSTANCE.getEPBSContext();

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
