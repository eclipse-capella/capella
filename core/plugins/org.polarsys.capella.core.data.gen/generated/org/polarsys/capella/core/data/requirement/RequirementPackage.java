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
package org.polarsys.capella.core.data.requirement;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.polarsys.capella.core.data.requirement.RequirementFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/dsl/2007/dslfactory trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Requirement aims at defining the requirements expression language.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='n/a' usage\040examples='none' constraints='This package depends on the model CapellaCore.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 * @generated
 */
public interface RequirementPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "requirement"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.polarsys.org/capella/core/requirement/1.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.polarsys.capella.core.data.requirement"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RequirementPackage eINSTANCE = org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl <em>Requirements Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getRequirementsPkg()
	 * @generated
	 */
	int REQUIREMENTS_PKG = 0;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__APPLIED_REQUIREMENTS = CapellacorePackage.STRUCTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__ADDITIONAL_INFORMATION = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__LEVEL = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_REQUIREMENTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Requirement Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Requirements Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.RequirementsTraceImpl <em>Requirements Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementsTraceImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getRequirementsTrace()
	 * @generated
	 */
	int REQUIREMENTS_TRACE = 1;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__OWNED_EXTENSIONS = CapellacorePackage.TRACE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__ID = CapellacorePackage.TRACE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__SID = CapellacorePackage.TRACE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__CONSTRAINTS = CapellacorePackage.TRACE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__OWNED_CONSTRAINTS = CapellacorePackage.TRACE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__REALIZED_FLOW = CapellacorePackage.TRACE__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__INCOMING_TRACES = CapellacorePackage.TRACE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__OUTGOING_TRACES = CapellacorePackage.TRACE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__VISIBLE_IN_DOC = CapellacorePackage.TRACE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__VISIBLE_IN_LM = CapellacorePackage.TRACE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__SUMMARY = CapellacorePackage.TRACE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__DESCRIPTION = CapellacorePackage.TRACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__REVIEW = CapellacorePackage.TRACE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__OWNED_PROPERTY_VALUES = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.TRACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__APPLIED_PROPERTY_VALUES = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__STATUS = CapellacorePackage.TRACE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__FEATURES = CapellacorePackage.TRACE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__APPLIED_REQUIREMENTS = CapellacorePackage.TRACE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__TARGET_ELEMENT = CapellacorePackage.TRACE__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__SOURCE_ELEMENT = CapellacorePackage.TRACE__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__SOURCE = CapellacorePackage.TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE__TARGET = CapellacorePackage.TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Requirements Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_TRACE_FEATURE_COUNT = CapellacorePackage.TRACE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.RequirementImpl <em>Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getRequirement()
	 * @generated
	 */
	int REQUIREMENT = 2;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OWNED_EXTENSIONS = CapellacorePackage.NAMESPACE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__ID = CapellacorePackage.NAMESPACE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__SID = CapellacorePackage.NAMESPACE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__CONSTRAINTS = CapellacorePackage.NAMESPACE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OWNED_CONSTRAINTS = CapellacorePackage.NAMESPACE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__NAME = CapellacorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__INCOMING_TRACES = CapellacorePackage.NAMESPACE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OUTGOING_TRACES = CapellacorePackage.NAMESPACE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__VISIBLE_IN_DOC = CapellacorePackage.NAMESPACE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__VISIBLE_IN_LM = CapellacorePackage.NAMESPACE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__SUMMARY = CapellacorePackage.NAMESPACE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__DESCRIPTION = CapellacorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__REVIEW = CapellacorePackage.NAMESPACE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__STATUS = CapellacorePackage.NAMESPACE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__FEATURES = CapellacorePackage.NAMESPACE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__APPLIED_REQUIREMENTS = CapellacorePackage.NAMESPACE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__OWNED_TRACES = CapellacorePackage.NAMESPACE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__CONTAINED_GENERIC_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__NAMING_RULES = CapellacorePackage.NAMESPACE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Is Obsolete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__IS_OBSOLETE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Requirement Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__REQUIREMENT_ID = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__ADDITIONAL_INFORMATION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Verification Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__VERIFICATION_METHOD = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Verification Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__VERIFICATION_PHASE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__IMPLEMENTATION_VERSION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__FEATURE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Related Capella Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__RELATED_CAPELLA_ELEMENTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_FEATURE_COUNT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemFunctionalInterfaceRequirementImpl <em>System Functional Interface Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.SystemFunctionalInterfaceRequirementImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemFunctionalInterfaceRequirement()
	 * @generated
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT = 3;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_EXTENSIONS = REQUIREMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__ID = REQUIREMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__SID = REQUIREMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__CONSTRAINTS = REQUIREMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_CONSTRAINTS = REQUIREMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__NAME = REQUIREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__INCOMING_TRACES = REQUIREMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OUTGOING_TRACES = REQUIREMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__VISIBLE_IN_DOC = REQUIREMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__VISIBLE_IN_LM = REQUIREMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__SUMMARY = REQUIREMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__DESCRIPTION = REQUIREMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__REVIEW = REQUIREMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_PROPERTY_VALUES = REQUIREMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES = REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__APPLIED_PROPERTY_VALUES = REQUIREMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS = REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS = REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__STATUS = REQUIREMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__FEATURES = REQUIREMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__APPLIED_REQUIREMENTS = REQUIREMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_TRACES = REQUIREMENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__CONTAINED_GENERIC_TRACES = REQUIREMENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES = REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__NAMING_RULES = REQUIREMENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Is Obsolete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__IS_OBSOLETE = REQUIREMENT__IS_OBSOLETE;

	/**
	 * The feature id for the '<em><b>Requirement Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__REQUIREMENT_ID = REQUIREMENT__REQUIREMENT_ID;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__ADDITIONAL_INFORMATION = REQUIREMENT__ADDITIONAL_INFORMATION;

	/**
	 * The feature id for the '<em><b>Verification Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__VERIFICATION_METHOD = REQUIREMENT__VERIFICATION_METHOD;

	/**
	 * The feature id for the '<em><b>Verification Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__VERIFICATION_PHASE = REQUIREMENT__VERIFICATION_PHASE;

	/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__IMPLEMENTATION_VERSION = REQUIREMENT__IMPLEMENTATION_VERSION;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__FEATURE = REQUIREMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Related Capella Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT__RELATED_CAPELLA_ELEMENTS = REQUIREMENT__RELATED_CAPELLA_ELEMENTS;

	/**
	 * The number of structural features of the '<em>System Functional Interface Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemFunctionalRequirementImpl <em>System Functional Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.SystemFunctionalRequirementImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemFunctionalRequirement()
	 * @generated
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT = 4;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OWNED_EXTENSIONS = REQUIREMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__ID = REQUIREMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__SID = REQUIREMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__CONSTRAINTS = REQUIREMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OWNED_CONSTRAINTS = REQUIREMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__NAME = REQUIREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__INCOMING_TRACES = REQUIREMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OUTGOING_TRACES = REQUIREMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__VISIBLE_IN_DOC = REQUIREMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__VISIBLE_IN_LM = REQUIREMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__SUMMARY = REQUIREMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__DESCRIPTION = REQUIREMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__REVIEW = REQUIREMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OWNED_PROPERTY_VALUES = REQUIREMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES = REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__APPLIED_PROPERTY_VALUES = REQUIREMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS = REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS = REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__STATUS = REQUIREMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__FEATURES = REQUIREMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__APPLIED_REQUIREMENTS = REQUIREMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__OWNED_TRACES = REQUIREMENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__CONTAINED_GENERIC_TRACES = REQUIREMENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES = REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__NAMING_RULES = REQUIREMENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Is Obsolete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__IS_OBSOLETE = REQUIREMENT__IS_OBSOLETE;

	/**
	 * The feature id for the '<em><b>Requirement Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__REQUIREMENT_ID = REQUIREMENT__REQUIREMENT_ID;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__ADDITIONAL_INFORMATION = REQUIREMENT__ADDITIONAL_INFORMATION;

	/**
	 * The feature id for the '<em><b>Verification Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__VERIFICATION_METHOD = REQUIREMENT__VERIFICATION_METHOD;

	/**
	 * The feature id for the '<em><b>Verification Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__VERIFICATION_PHASE = REQUIREMENT__VERIFICATION_PHASE;

	/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__IMPLEMENTATION_VERSION = REQUIREMENT__IMPLEMENTATION_VERSION;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__FEATURE = REQUIREMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Related Capella Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT__RELATED_CAPELLA_ELEMENTS = REQUIREMENT__RELATED_CAPELLA_ELEMENTS;

	/**
	 * The number of structural features of the '<em>System Functional Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTIONAL_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalInterfaceRequirementImpl <em>System Non Functional Interface Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalInterfaceRequirementImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemNonFunctionalInterfaceRequirement()
	 * @generated
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT = 5;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_EXTENSIONS = REQUIREMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__ID = REQUIREMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__SID = REQUIREMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__CONSTRAINTS = REQUIREMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_CONSTRAINTS = REQUIREMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__NAME = REQUIREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__INCOMING_TRACES = REQUIREMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OUTGOING_TRACES = REQUIREMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__VISIBLE_IN_DOC = REQUIREMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__VISIBLE_IN_LM = REQUIREMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__SUMMARY = REQUIREMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__DESCRIPTION = REQUIREMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__REVIEW = REQUIREMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_PROPERTY_VALUES = REQUIREMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES = REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__APPLIED_PROPERTY_VALUES = REQUIREMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS = REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS = REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__STATUS = REQUIREMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__FEATURES = REQUIREMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__APPLIED_REQUIREMENTS = REQUIREMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__OWNED_TRACES = REQUIREMENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__CONTAINED_GENERIC_TRACES = REQUIREMENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES = REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__NAMING_RULES = REQUIREMENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Is Obsolete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__IS_OBSOLETE = REQUIREMENT__IS_OBSOLETE;

	/**
	 * The feature id for the '<em><b>Requirement Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__REQUIREMENT_ID = REQUIREMENT__REQUIREMENT_ID;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__ADDITIONAL_INFORMATION = REQUIREMENT__ADDITIONAL_INFORMATION;

	/**
	 * The feature id for the '<em><b>Verification Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__VERIFICATION_METHOD = REQUIREMENT__VERIFICATION_METHOD;

	/**
	 * The feature id for the '<em><b>Verification Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__VERIFICATION_PHASE = REQUIREMENT__VERIFICATION_PHASE;

	/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__IMPLEMENTATION_VERSION = REQUIREMENT__IMPLEMENTATION_VERSION;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__FEATURE = REQUIREMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Related Capella Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT__RELATED_CAPELLA_ELEMENTS = REQUIREMENT__RELATED_CAPELLA_ELEMENTS;

	/**
	 * The number of structural features of the '<em>System Non Functional Interface Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalRequirementImpl <em>System Non Functional Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalRequirementImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemNonFunctionalRequirement()
	 * @generated
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT = 6;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OWNED_EXTENSIONS = REQUIREMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__ID = REQUIREMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__SID = REQUIREMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__CONSTRAINTS = REQUIREMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OWNED_CONSTRAINTS = REQUIREMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__NAME = REQUIREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__INCOMING_TRACES = REQUIREMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OUTGOING_TRACES = REQUIREMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__VISIBLE_IN_DOC = REQUIREMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__VISIBLE_IN_LM = REQUIREMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__SUMMARY = REQUIREMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__DESCRIPTION = REQUIREMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__REVIEW = REQUIREMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OWNED_PROPERTY_VALUES = REQUIREMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES = REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__APPLIED_PROPERTY_VALUES = REQUIREMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS = REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS = REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__STATUS = REQUIREMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__FEATURES = REQUIREMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__APPLIED_REQUIREMENTS = REQUIREMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__OWNED_TRACES = REQUIREMENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__CONTAINED_GENERIC_TRACES = REQUIREMENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES = REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__NAMING_RULES = REQUIREMENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Is Obsolete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__IS_OBSOLETE = REQUIREMENT__IS_OBSOLETE;

	/**
	 * The feature id for the '<em><b>Requirement Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__REQUIREMENT_ID = REQUIREMENT__REQUIREMENT_ID;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__ADDITIONAL_INFORMATION = REQUIREMENT__ADDITIONAL_INFORMATION;

	/**
	 * The feature id for the '<em><b>Verification Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__VERIFICATION_METHOD = REQUIREMENT__VERIFICATION_METHOD;

	/**
	 * The feature id for the '<em><b>Verification Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__VERIFICATION_PHASE = REQUIREMENT__VERIFICATION_PHASE;

	/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__IMPLEMENTATION_VERSION = REQUIREMENT__IMPLEMENTATION_VERSION;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__FEATURE = REQUIREMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Related Capella Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT__RELATED_CAPELLA_ELEMENTS = REQUIREMENT__RELATED_CAPELLA_ELEMENTS;

	/**
	 * The number of structural features of the '<em>System Non Functional Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_NON_FUNCTIONAL_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemUserRequirementImpl <em>System User Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.requirement.impl.SystemUserRequirementImpl
	 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemUserRequirement()
	 * @generated
	 */
	int SYSTEM_USER_REQUIREMENT = 7;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OWNED_EXTENSIONS = REQUIREMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__ID = REQUIREMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__SID = REQUIREMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__CONSTRAINTS = REQUIREMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OWNED_CONSTRAINTS = REQUIREMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__NAME = REQUIREMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__INCOMING_TRACES = REQUIREMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OUTGOING_TRACES = REQUIREMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__VISIBLE_IN_DOC = REQUIREMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__VISIBLE_IN_LM = REQUIREMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__SUMMARY = REQUIREMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__DESCRIPTION = REQUIREMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__REVIEW = REQUIREMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OWNED_PROPERTY_VALUES = REQUIREMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES = REQUIREMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__APPLIED_PROPERTY_VALUES = REQUIREMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS = REQUIREMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS = REQUIREMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__STATUS = REQUIREMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__FEATURES = REQUIREMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__APPLIED_REQUIREMENTS = REQUIREMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__OWNED_TRACES = REQUIREMENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__CONTAINED_GENERIC_TRACES = REQUIREMENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES = REQUIREMENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__NAMING_RULES = REQUIREMENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Is Obsolete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__IS_OBSOLETE = REQUIREMENT__IS_OBSOLETE;

	/**
	 * The feature id for the '<em><b>Requirement Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__REQUIREMENT_ID = REQUIREMENT__REQUIREMENT_ID;

	/**
	 * The feature id for the '<em><b>Additional Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__ADDITIONAL_INFORMATION = REQUIREMENT__ADDITIONAL_INFORMATION;

	/**
	 * The feature id for the '<em><b>Verification Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__VERIFICATION_METHOD = REQUIREMENT__VERIFICATION_METHOD;

	/**
	 * The feature id for the '<em><b>Verification Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__VERIFICATION_PHASE = REQUIREMENT__VERIFICATION_PHASE;

	/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__IMPLEMENTATION_VERSION = REQUIREMENT__IMPLEMENTATION_VERSION;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__FEATURE = REQUIREMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Related Capella Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT__RELATED_CAPELLA_ELEMENTS = REQUIREMENT__RELATED_CAPELLA_ELEMENTS;

	/**
	 * The number of structural features of the '<em>System User Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_USER_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg <em>Requirements Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirements Pkg</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsPkg
	 * @generated
	 */
	EClass getRequirementsPkg();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getAdditionalInformation <em>Additional Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Information</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsPkg#getAdditionalInformation()
	 * @see #getRequirementsPkg()
	 * @generated
	 */
	EAttribute getRequirementsPkg_AdditionalInformation();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsPkg#getLevel()
	 * @see #getRequirementsPkg()
	 * @generated
	 */
	EAttribute getRequirementsPkg_Level();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getOwnedRequirements <em>Owned Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Requirements</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsPkg#getOwnedRequirements()
	 * @see #getRequirementsPkg()
	 * @generated
	 */
	EReference getRequirementsPkg_OwnedRequirements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getOwnedRequirementPkgs <em>Owned Requirement Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Requirement Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsPkg#getOwnedRequirementPkgs()
	 * @see #getRequirementsPkg()
	 * @generated
	 */
	EReference getRequirementsPkg_OwnedRequirementPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.RequirementsTrace <em>Requirements Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirements Trace</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsTrace
	 * @generated
	 */
	EClass getRequirementsTrace();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.requirement.RequirementsTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsTrace#getSource()
	 * @see #getRequirementsTrace()
	 * @generated
	 */
	EReference getRequirementsTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.requirement.RequirementsTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.polarsys.capella.core.data.requirement.RequirementsTrace#getTarget()
	 * @see #getRequirementsTrace()
	 * @generated
	 */
	EReference getRequirementsTrace_Target();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement
	 * @generated
	 */
	EClass getRequirement();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#isIsObsolete <em>Is Obsolete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Obsolete</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#isIsObsolete()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_IsObsolete();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#getRequirementId <em>Requirement Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requirement Id</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getRequirementId()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_RequirementId();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#getAdditionalInformation <em>Additional Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Additional Information</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getAdditionalInformation()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_AdditionalInformation();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#getVerificationMethod <em>Verification Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verification Method</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getVerificationMethod()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_VerificationMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#getVerificationPhase <em>Verification Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verification Phase</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getVerificationPhase()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_VerificationPhase();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#getImplementationVersion <em>Implementation Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Version</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getImplementationVersion()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_ImplementationVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.requirement.Requirement#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getFeature()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_Feature();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.requirement.Requirement#getRelatedCapellaElements <em>Related Capella Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Related Capella Elements</em>'.
	 * @see org.polarsys.capella.core.data.requirement.Requirement#getRelatedCapellaElements()
	 * @see #getRequirement()
	 * @generated
	 */
	EReference getRequirement_RelatedCapellaElements();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement <em>System Functional Interface Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Functional Interface Requirement</em>'.
	 * @see org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement
	 * @generated
	 */
	EClass getSystemFunctionalInterfaceRequirement();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement <em>System Functional Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Functional Requirement</em>'.
	 * @see org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement
	 * @generated
	 */
	EClass getSystemFunctionalRequirement();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement <em>System Non Functional Interface Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Non Functional Interface Requirement</em>'.
	 * @see org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement
	 * @generated
	 */
	EClass getSystemNonFunctionalInterfaceRequirement();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement <em>System Non Functional Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Non Functional Requirement</em>'.
	 * @see org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement
	 * @generated
	 */
	EClass getSystemNonFunctionalRequirement();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.requirement.SystemUserRequirement <em>System User Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System User Requirement</em>'.
	 * @see org.polarsys.capella.core.data.requirement.SystemUserRequirement
	 * @generated
	 */
	EClass getSystemUserRequirement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RequirementFactory getRequirementFactory();

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
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl <em>Requirements Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getRequirementsPkg()
		 * @generated
		 */
		EClass REQUIREMENTS_PKG = eINSTANCE.getRequirementsPkg();

		/**
		 * The meta object literal for the '<em><b>Additional Information</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENTS_PKG__ADDITIONAL_INFORMATION = eINSTANCE.getRequirementsPkg_AdditionalInformation();

		/**
		 * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENTS_PKG__LEVEL = eINSTANCE.getRequirementsPkg_Level();

		/**
		 * The meta object literal for the '<em><b>Owned Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENTS_PKG__OWNED_REQUIREMENTS = eINSTANCE.getRequirementsPkg_OwnedRequirements();

		/**
		 * The meta object literal for the '<em><b>Owned Requirement Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS = eINSTANCE.getRequirementsPkg_OwnedRequirementPkgs();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.RequirementsTraceImpl <em>Requirements Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementsTraceImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getRequirementsTrace()
		 * @generated
		 */
		EClass REQUIREMENTS_TRACE = eINSTANCE.getRequirementsTrace();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENTS_TRACE__SOURCE = eINSTANCE.getRequirementsTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENTS_TRACE__TARGET = eINSTANCE.getRequirementsTrace_Target();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.RequirementImpl <em>Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getRequirement()
		 * @generated
		 */
		EClass REQUIREMENT = eINSTANCE.getRequirement();

		/**
		 * The meta object literal for the '<em><b>Is Obsolete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__IS_OBSOLETE = eINSTANCE.getRequirement_IsObsolete();

		/**
		 * The meta object literal for the '<em><b>Requirement Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__REQUIREMENT_ID = eINSTANCE.getRequirement_RequirementId();

		/**
		 * The meta object literal for the '<em><b>Additional Information</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__ADDITIONAL_INFORMATION = eINSTANCE.getRequirement_AdditionalInformation();

		/**
		 * The meta object literal for the '<em><b>Verification Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__VERIFICATION_METHOD = eINSTANCE.getRequirement_VerificationMethod();

		/**
		 * The meta object literal for the '<em><b>Verification Phase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__VERIFICATION_PHASE = eINSTANCE.getRequirement_VerificationPhase();

		/**
		 * The meta object literal for the '<em><b>Implementation Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__IMPLEMENTATION_VERSION = eINSTANCE.getRequirement_ImplementationVersion();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__FEATURE = eINSTANCE.getRequirement_Feature();

		/**
		 * The meta object literal for the '<em><b>Related Capella Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT__RELATED_CAPELLA_ELEMENTS = eINSTANCE.getRequirement_RelatedCapellaElements();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemFunctionalInterfaceRequirementImpl <em>System Functional Interface Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.SystemFunctionalInterfaceRequirementImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemFunctionalInterfaceRequirement()
		 * @generated
		 */
		EClass SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT = eINSTANCE.getSystemFunctionalInterfaceRequirement();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemFunctionalRequirementImpl <em>System Functional Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.SystemFunctionalRequirementImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemFunctionalRequirement()
		 * @generated
		 */
		EClass SYSTEM_FUNCTIONAL_REQUIREMENT = eINSTANCE.getSystemFunctionalRequirement();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalInterfaceRequirementImpl <em>System Non Functional Interface Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalInterfaceRequirementImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemNonFunctionalInterfaceRequirement()
		 * @generated
		 */
		EClass SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT = eINSTANCE.getSystemNonFunctionalInterfaceRequirement();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalRequirementImpl <em>System Non Functional Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.SystemNonFunctionalRequirementImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemNonFunctionalRequirement()
		 * @generated
		 */
		EClass SYSTEM_NON_FUNCTIONAL_REQUIREMENT = eINSTANCE.getSystemNonFunctionalRequirement();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.requirement.impl.SystemUserRequirementImpl <em>System User Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.requirement.impl.SystemUserRequirementImpl
		 * @see org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl#getSystemUserRequirement()
		 * @generated
		 */
		EClass SYSTEM_USER_REQUIREMENT = eINSTANCE.getSystemUserRequirement();

	}

} //RequirementPackage
