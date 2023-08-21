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
package org.polarsys.capella.core.data.capellacommon;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping profileName='Capella'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='CapellaCommon aims at defining other concepts (mainly used to solve the constraints arisen from the 4.2.1 rationale). It concretises the Activity and the State machines.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model CapellaCore.ecore\r\nThis package depends on the model Activity.ecore\r\nThis package depends on the model StateMachine.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CapellacommonPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "capellacommon"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/common/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.capellacommon"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CapellacommonPackage eINSTANCE = org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractCapabilityPkgImpl <em>Abstract Capability Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.AbstractCapabilityPkgImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getAbstractCapabilityPkg()
   * @generated
   */
	int ABSTRACT_CAPABILITY_PKG = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The number of structural features of the '<em>Abstract Capability Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.GenericTraceImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getGenericTrace()
   * @generated
   */
	int GENERIC_TRACE = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OWNED_EXTENSIONS = CapellacorePackage.TRACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__ID = CapellacorePackage.TRACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__SID = CapellacorePackage.TRACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__CONSTRAINTS = CapellacorePackage.TRACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OWNED_CONSTRAINTS = CapellacorePackage.TRACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.TRACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__REALIZED_FLOW = CapellacorePackage.TRACE__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__INCOMING_TRACES = CapellacorePackage.TRACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OUTGOING_TRACES = CapellacorePackage.TRACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__VISIBLE_IN_DOC = CapellacorePackage.TRACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__VISIBLE_IN_LM = CapellacorePackage.TRACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__SUMMARY = CapellacorePackage.TRACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__DESCRIPTION = CapellacorePackage.TRACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__REVIEW = CapellacorePackage.TRACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OWNED_PROPERTY_VALUES = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.TRACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__APPLIED_PROPERTY_VALUES = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__STATUS = CapellacorePackage.TRACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__FEATURES = CapellacorePackage.TRACE__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__TARGET_ELEMENT = CapellacorePackage.TRACE__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__SOURCE_ELEMENT = CapellacorePackage.TRACE__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Key Value Pairs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__KEY_VALUE_PAIRS = CapellacorePackage.TRACE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__SOURCE = CapellacorePackage.TRACE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE__TARGET = CapellacorePackage.TRACE_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Generic Trace</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_TRACE_FEATURE_COUNT = CapellacorePackage.TRACE_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.TransfoLinkImpl <em>Transfo Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.TransfoLinkImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTransfoLink()
   * @generated
   */
	int TRANSFO_LINK = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OWNED_EXTENSIONS = GENERIC_TRACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__ID = GENERIC_TRACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__SID = GENERIC_TRACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__CONSTRAINTS = GENERIC_TRACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OWNED_CONSTRAINTS = GENERIC_TRACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OWNED_MIGRATED_ELEMENTS = GENERIC_TRACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__REALIZED_FLOW = GENERIC_TRACE__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__INCOMING_TRACES = GENERIC_TRACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OUTGOING_TRACES = GENERIC_TRACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__VISIBLE_IN_DOC = GENERIC_TRACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__VISIBLE_IN_LM = GENERIC_TRACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__SUMMARY = GENERIC_TRACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__DESCRIPTION = GENERIC_TRACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__REVIEW = GENERIC_TRACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OWNED_PROPERTY_VALUES = GENERIC_TRACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = GENERIC_TRACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__APPLIED_PROPERTY_VALUES = GENERIC_TRACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__OWNED_PROPERTY_VALUE_GROUPS = GENERIC_TRACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__APPLIED_PROPERTY_VALUE_GROUPS = GENERIC_TRACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__STATUS = GENERIC_TRACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__FEATURES = GENERIC_TRACE__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__TARGET_ELEMENT = GENERIC_TRACE__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__SOURCE_ELEMENT = GENERIC_TRACE__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Key Value Pairs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__KEY_VALUE_PAIRS = GENERIC_TRACE__KEY_VALUE_PAIRS;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__SOURCE = GENERIC_TRACE__SOURCE;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK__TARGET = GENERIC_TRACE__TARGET;

	/**
   * The number of structural features of the '<em>Transfo Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFO_LINK_FEATURE_COUNT = GENERIC_TRACE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.JustificationLinkImpl <em>Justification Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.JustificationLinkImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getJustificationLink()
   * @generated
   */
	int JUSTIFICATION_LINK = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OWNED_EXTENSIONS = GENERIC_TRACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__ID = GENERIC_TRACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__SID = GENERIC_TRACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__CONSTRAINTS = GENERIC_TRACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OWNED_CONSTRAINTS = GENERIC_TRACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OWNED_MIGRATED_ELEMENTS = GENERIC_TRACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__REALIZED_FLOW = GENERIC_TRACE__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__INCOMING_TRACES = GENERIC_TRACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OUTGOING_TRACES = GENERIC_TRACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__VISIBLE_IN_DOC = GENERIC_TRACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__VISIBLE_IN_LM = GENERIC_TRACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__SUMMARY = GENERIC_TRACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__DESCRIPTION = GENERIC_TRACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__REVIEW = GENERIC_TRACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OWNED_PROPERTY_VALUES = GENERIC_TRACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = GENERIC_TRACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__APPLIED_PROPERTY_VALUES = GENERIC_TRACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__OWNED_PROPERTY_VALUE_GROUPS = GENERIC_TRACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__APPLIED_PROPERTY_VALUE_GROUPS = GENERIC_TRACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__STATUS = GENERIC_TRACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__FEATURES = GENERIC_TRACE__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__TARGET_ELEMENT = GENERIC_TRACE__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__SOURCE_ELEMENT = GENERIC_TRACE__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Key Value Pairs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__KEY_VALUE_PAIRS = GENERIC_TRACE__KEY_VALUE_PAIRS;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__SOURCE = GENERIC_TRACE__SOURCE;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK__TARGET = GENERIC_TRACE__TARGET;

	/**
   * The number of structural features of the '<em>Justification Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JUSTIFICATION_LINK_FEATURE_COUNT = GENERIC_TRACE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl <em>Capability Realization Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getCapabilityRealizationInvolvement()
   * @generated
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Involved Capability Realization Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Capability Realization Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvedElementImpl <em>Capability Realization Involved Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvedElementImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getCapabilityRealizationInvolvedElement()
   * @generated
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__ID = CapellacorePackage.INVOLVED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__SID = CapellacorePackage.INVOLVED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CONSTRAINTS = CapellacorePackage.INVOLVED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__SUMMARY = CapellacorePackage.INVOLVED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__DESCRIPTION = CapellacorePackage.INVOLVED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__REVIEW = CapellacorePackage.INVOLVED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__STATUS = CapellacorePackage.INVOLVED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__FEATURES = CapellacorePackage.INVOLVED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS = CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Capability Realization Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS = CapellacorePackage.INVOLVED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS = CapellacorePackage.INVOLVED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Capability Realization Involved Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl <em>State Machine</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateMachine()
   * @generated
   */
	int STATE_MACHINE = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__NAME = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Control Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__IS_CONTROL_OPERATOR = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Parameter Set</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_PARAMETER_SET = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_PARAMETER = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Regions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_REGIONS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Connection Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE__OWNED_CONNECTION_POINTS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>State Machine</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_MACHINE_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.RegionImpl <em>Region</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.RegionImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getRegion()
   * @generated
   */
	int REGION = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned States</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Transitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__OWNED_TRANSITIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Involved States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION__INVOLVED_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Region</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REGION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl <em>Abstract State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getAbstractState()
   * @generated
   */
	int ABSTRACT_STATE = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__REFERENCED_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACT_STATE__EXPLOITED_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__REALIZED_ABSTRACT_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__REALIZING_ABSTRACT_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__OUTGOING = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__INCOMING = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE__INVOLVER_REGIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The number of structural features of the '<em>Abstract State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl <em>State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.StateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getState()
   * @generated
   */
	int STATE = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_EXTENSIONS = ABSTRACT_STATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__ID = ABSTRACT_STATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__SID = ABSTRACT_STATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__CONSTRAINTS = ABSTRACT_STATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_CONSTRAINTS = ABSTRACT_STATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_STATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__NAME = ABSTRACT_STATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__INCOMING_TRACES = ABSTRACT_STATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OUTGOING_TRACES = ABSTRACT_STATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__VISIBLE_IN_DOC = ABSTRACT_STATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__VISIBLE_IN_LM = ABSTRACT_STATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__SUMMARY = ABSTRACT_STATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__DESCRIPTION = ABSTRACT_STATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__REVIEW = ABSTRACT_STATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_PROPERTY_VALUES = ABSTRACT_STATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_STATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__APPLIED_PROPERTY_VALUES = ABSTRACT_STATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_STATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_STATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__STATUS = ABSTRACT_STATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__FEATURES = ABSTRACT_STATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__REFERENCED_STATES = ABSTRACT_STATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__EXPLOITED_STATES = ABSTRACT_STATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__REALIZED_ABSTRACT_STATES = ABSTRACT_STATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__REALIZING_ABSTRACT_STATES = ABSTRACT_STATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OUTGOING = ABSTRACT_STATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__INCOMING = ABSTRACT_STATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__INVOLVER_REGIONS = ABSTRACT_STATE__INVOLVER_REGIONS;

	/**
   * The feature id for the '<em><b>Owned Regions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_REGIONS = ABSTRACT_STATE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Connection Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__OWNED_CONNECTION_POINTS = ABSTRACT_STATE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Available Abstract Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__AVAILABLE_ABSTRACT_FUNCTIONS = ABSTRACT_STATE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Available Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__AVAILABLE_FUNCTIONAL_CHAINS = ABSTRACT_STATE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Available Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__AVAILABLE_ABSTRACT_CAPABILITIES = ABSTRACT_STATE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Entry</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__ENTRY = ABSTRACT_STATE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Do Activity</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__DO_ACTIVITY = ABSTRACT_STATE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Exit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__EXIT = ABSTRACT_STATE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>State Invariant</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE__STATE_INVARIANT = ABSTRACT_STATE_FEATURE_COUNT + 8;

	/**
   * The number of structural features of the '<em>State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FEATURE_COUNT = ABSTRACT_STATE_FEATURE_COUNT + 9;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ModeImpl <em>Mode</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.ModeImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getMode()
   * @generated
   */
	int MODE = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_EXTENSIONS = STATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__ID = STATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__SID = STATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__CONSTRAINTS = STATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_CONSTRAINTS = STATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_MIGRATED_ELEMENTS = STATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__NAME = STATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__INCOMING_TRACES = STATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OUTGOING_TRACES = STATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__VISIBLE_IN_DOC = STATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__VISIBLE_IN_LM = STATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__SUMMARY = STATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__DESCRIPTION = STATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__REVIEW = STATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_PROPERTY_VALUES = STATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_ENUMERATION_PROPERTY_TYPES = STATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__APPLIED_PROPERTY_VALUES = STATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_PROPERTY_VALUE_GROUPS = STATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__APPLIED_PROPERTY_VALUE_GROUPS = STATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__STATUS = STATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__FEATURES = STATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__REFERENCED_STATES = STATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODE__EXPLOITED_STATES = STATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_ABSTRACT_STATE_REALIZATIONS = STATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__REALIZED_ABSTRACT_STATES = STATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__REALIZING_ABSTRACT_STATES = STATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OUTGOING = STATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__INCOMING = STATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__INVOLVER_REGIONS = STATE__INVOLVER_REGIONS;

	/**
   * The feature id for the '<em><b>Owned Regions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_REGIONS = STATE__OWNED_REGIONS;

	/**
   * The feature id for the '<em><b>Owned Connection Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__OWNED_CONNECTION_POINTS = STATE__OWNED_CONNECTION_POINTS;

	/**
   * The feature id for the '<em><b>Available Abstract Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__AVAILABLE_ABSTRACT_FUNCTIONS = STATE__AVAILABLE_ABSTRACT_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Available Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__AVAILABLE_FUNCTIONAL_CHAINS = STATE__AVAILABLE_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Available Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__AVAILABLE_ABSTRACT_CAPABILITIES = STATE__AVAILABLE_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Entry</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__ENTRY = STATE__ENTRY;

	/**
   * The feature id for the '<em><b>Do Activity</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__DO_ACTIVITY = STATE__DO_ACTIVITY;

	/**
   * The feature id for the '<em><b>Exit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__EXIT = STATE__EXIT;

	/**
   * The feature id for the '<em><b>State Invariant</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE__STATE_INVARIANT = STATE__STATE_INVARIANT;

	/**
   * The number of structural features of the '<em>Mode</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.FinalStateImpl <em>Final State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.FinalStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getFinalState()
   * @generated
   */
	int FINAL_STATE = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_EXTENSIONS = STATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__ID = STATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__SID = STATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__CONSTRAINTS = STATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_CONSTRAINTS = STATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_MIGRATED_ELEMENTS = STATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__NAME = STATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__INCOMING_TRACES = STATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OUTGOING_TRACES = STATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__VISIBLE_IN_DOC = STATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__VISIBLE_IN_LM = STATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__SUMMARY = STATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__DESCRIPTION = STATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__REVIEW = STATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_PROPERTY_VALUES = STATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = STATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__APPLIED_PROPERTY_VALUES = STATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_PROPERTY_VALUE_GROUPS = STATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__APPLIED_PROPERTY_VALUE_GROUPS = STATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__STATUS = STATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__FEATURES = STATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__REFERENCED_STATES = STATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FINAL_STATE__EXPLOITED_STATES = STATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = STATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__REALIZED_ABSTRACT_STATES = STATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__REALIZING_ABSTRACT_STATES = STATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OUTGOING = STATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__INCOMING = STATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__INVOLVER_REGIONS = STATE__INVOLVER_REGIONS;

	/**
   * The feature id for the '<em><b>Owned Regions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_REGIONS = STATE__OWNED_REGIONS;

	/**
   * The feature id for the '<em><b>Owned Connection Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__OWNED_CONNECTION_POINTS = STATE__OWNED_CONNECTION_POINTS;

	/**
   * The feature id for the '<em><b>Available Abstract Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__AVAILABLE_ABSTRACT_FUNCTIONS = STATE__AVAILABLE_ABSTRACT_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Available Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__AVAILABLE_FUNCTIONAL_CHAINS = STATE__AVAILABLE_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Available Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__AVAILABLE_ABSTRACT_CAPABILITIES = STATE__AVAILABLE_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Entry</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__ENTRY = STATE__ENTRY;

	/**
   * The feature id for the '<em><b>Do Activity</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__DO_ACTIVITY = STATE__DO_ACTIVITY;

	/**
   * The feature id for the '<em><b>Exit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__EXIT = STATE__EXIT;

	/**
   * The feature id for the '<em><b>State Invariant</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE__STATE_INVARIANT = STATE__STATE_INVARIANT;

	/**
   * The number of structural features of the '<em>Final State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINAL_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl <em>State Transition</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateTransition()
   * @generated
   */
	int STATE_TRANSITION = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__REALIZED_FLOW = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__KIND = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Trigger Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__TRIGGER_DESCRIPTION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Guard</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__GUARD = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__SOURCE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__TARGET = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Effect</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__EFFECT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Triggers</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__TRIGGERS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned State Transition Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Realized State Transitions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__REALIZED_STATE_TRANSITIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Realizing State Transitions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION__REALIZING_STATE_TRANSITIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The number of structural features of the '<em>State Transition</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.PseudostateImpl <em>Pseudostate</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.PseudostateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getPseudostate()
   * @generated
   */
	int PSEUDOSTATE = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_EXTENSIONS = ABSTRACT_STATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__ID = ABSTRACT_STATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__SID = ABSTRACT_STATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__CONSTRAINTS = ABSTRACT_STATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_CONSTRAINTS = ABSTRACT_STATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_STATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__NAME = ABSTRACT_STATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__INCOMING_TRACES = ABSTRACT_STATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OUTGOING_TRACES = ABSTRACT_STATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__VISIBLE_IN_DOC = ABSTRACT_STATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__VISIBLE_IN_LM = ABSTRACT_STATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__SUMMARY = ABSTRACT_STATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__DESCRIPTION = ABSTRACT_STATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__REVIEW = ABSTRACT_STATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_PROPERTY_VALUES = ABSTRACT_STATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_STATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__APPLIED_PROPERTY_VALUES = ABSTRACT_STATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_STATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_STATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__STATUS = ABSTRACT_STATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__FEATURES = ABSTRACT_STATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__REFERENCED_STATES = ABSTRACT_STATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PSEUDOSTATE__EXPLOITED_STATES = ABSTRACT_STATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS = ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__REALIZED_ABSTRACT_STATES = ABSTRACT_STATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__REALIZING_ABSTRACT_STATES = ABSTRACT_STATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__OUTGOING = ABSTRACT_STATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__INCOMING = ABSTRACT_STATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE__INVOLVER_REGIONS = ABSTRACT_STATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Pseudostate</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PSEUDOSTATE_FEATURE_COUNT = ABSTRACT_STATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.InitialPseudoStateImpl <em>Initial Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.InitialPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getInitialPseudoState()
   * @generated
   */
	int INITIAL_PSEUDO_STATE = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Initial Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INITIAL_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.JoinPseudoStateImpl <em>Join Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.JoinPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getJoinPseudoState()
   * @generated
   */
	int JOIN_PSEUDO_STATE = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOIN_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Join Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int JOIN_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ForkPseudoStateImpl <em>Fork Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.ForkPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getForkPseudoState()
   * @generated
   */
	int FORK_PSEUDO_STATE = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORK_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Fork Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FORK_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ChoicePseudoStateImpl <em>Choice Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.ChoicePseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getChoicePseudoState()
   * @generated
   */
	int CHOICE_PSEUDO_STATE = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHOICE_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Choice Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHOICE_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.TerminatePseudoStateImpl <em>Terminate Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.TerminatePseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTerminatePseudoState()
   * @generated
   */
	int TERMINATE_PSEUDO_STATE = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TERMINATE_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Terminate Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TERMINATE_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateRealizationImpl <em>Abstract State Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.AbstractStateRealizationImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getAbstractStateRealization()
   * @generated
   */
	int ABSTRACT_STATE_REALIZATION = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Abstract State</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__REALIZED_ABSTRACT_STATE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Abstract State</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION__REALIZING_ABSTRACT_STATE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract State Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STATE_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionRealizationImpl <em>State Transition Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.StateTransitionRealizationImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateTransitionRealization()
   * @generated
   */
	int STATE_TRANSITION_REALIZATION = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized State Transition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__REALIZED_STATE_TRANSITION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing State Transition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION__REALIZING_STATE_TRANSITION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>State Transition Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_TRANSITION_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ShallowHistoryPseudoStateImpl <em>Shallow History Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.ShallowHistoryPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getShallowHistoryPseudoState()
   * @generated
   */
	int SHALLOW_HISTORY_PSEUDO_STATE = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SHALLOW_HISTORY_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Shallow History Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHALLOW_HISTORY_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.DeepHistoryPseudoStateImpl <em>Deep History Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.DeepHistoryPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getDeepHistoryPseudoState()
   * @generated
   */
	int DEEP_HISTORY_PSEUDO_STATE = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEEP_HISTORY_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Deep History Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEEP_HISTORY_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.EntryPointPseudoStateImpl <em>Entry Point Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.EntryPointPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getEntryPointPseudoState()
   * @generated
   */
	int ENTRY_POINT_PSEUDO_STATE = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTRY_POINT_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Entry Point Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTRY_POINT_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ExitPointPseudoStateImpl <em>Exit Point Pseudo State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.ExitPointPseudoStateImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getExitPointPseudoState()
   * @generated
   */
	int EXIT_POINT_PSEUDO_STATE = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_EXTENSIONS = PSEUDOSTATE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__ID = PSEUDOSTATE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__SID = PSEUDOSTATE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__CONSTRAINTS = PSEUDOSTATE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_CONSTRAINTS = PSEUDOSTATE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_MIGRATED_ELEMENTS = PSEUDOSTATE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__NAME = PSEUDOSTATE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__INCOMING_TRACES = PSEUDOSTATE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OUTGOING_TRACES = PSEUDOSTATE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__VISIBLE_IN_DOC = PSEUDOSTATE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__VISIBLE_IN_LM = PSEUDOSTATE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__SUMMARY = PSEUDOSTATE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__DESCRIPTION = PSEUDOSTATE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__REVIEW = PSEUDOSTATE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_PROPERTY_VALUES = PSEUDOSTATE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = PSEUDOSTATE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__APPLIED_PROPERTY_VALUES = PSEUDOSTATE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__APPLIED_PROPERTY_VALUE_GROUPS = PSEUDOSTATE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__STATUS = PSEUDOSTATE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__FEATURES = PSEUDOSTATE__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__REFERENCED_STATES = PSEUDOSTATE__REFERENCED_STATES;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT_POINT_PSEUDO_STATE__EXPLOITED_STATES = PSEUDOSTATE__EXPLOITED_STATES;

  /**
   * The feature id for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = PSEUDOSTATE__OWNED_ABSTRACT_STATE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__REALIZED_ABSTRACT_STATES = PSEUDOSTATE__REALIZED_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Realizing Abstract States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__REALIZING_ABSTRACT_STATES = PSEUDOSTATE__REALIZING_ABSTRACT_STATES;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__OUTGOING = PSEUDOSTATE__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__INCOMING = PSEUDOSTATE__INCOMING;

	/**
   * The feature id for the '<em><b>Involver Regions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE__INVOLVER_REGIONS = PSEUDOSTATE__INVOLVER_REGIONS;

	/**
   * The number of structural features of the '<em>Exit Point Pseudo State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXIT_POINT_PSEUDO_STATE_FEATURE_COUNT = PSEUDOSTATE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateEventRealizationImpl <em>State Event Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.StateEventRealizationImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateEventRealization()
   * @generated
   */
	int STATE_EVENT_REALIZATION = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Event</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__REALIZED_EVENT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Event</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION__REALIZING_EVENT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>State Event Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateEventImpl <em>State Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.StateEventImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateEvent()
   * @generated
   */
	int STATE_EVENT = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Expression</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__EXPRESSION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned State Event Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>State Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_EVENT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ChangeEventImpl <em>Change Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.ChangeEventImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getChangeEvent()
   * @generated
   */
	int CHANGE_EVENT = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_EXTENSIONS = STATE_EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__ID = STATE_EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__SID = STATE_EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__CONSTRAINTS = STATE_EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_CONSTRAINTS = STATE_EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_MIGRATED_ELEMENTS = STATE_EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__NAME = STATE_EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__INCOMING_TRACES = STATE_EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OUTGOING_TRACES = STATE_EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__VISIBLE_IN_DOC = STATE_EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__VISIBLE_IN_LM = STATE_EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__SUMMARY = STATE_EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__DESCRIPTION = STATE_EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__REVIEW = STATE_EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_PROPERTY_VALUES = STATE_EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = STATE_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__APPLIED_PROPERTY_VALUES = STATE_EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_PROPERTY_VALUE_GROUPS = STATE_EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = STATE_EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__STATUS = STATE_EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__FEATURES = STATE_EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__ABSTRACT_TYPED_ELEMENTS = STATE_EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Expression</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__EXPRESSION = STATE_EVENT__EXPRESSION;

	/**
   * The feature id for the '<em><b>Owned State Event Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__OWNED_STATE_EVENT_REALIZATIONS = STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT__KIND = STATE_EVENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Change Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHANGE_EVENT_FEATURE_COUNT = STATE_EVENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.impl.TimeEventImpl <em>Time Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.impl.TimeEventImpl
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTimeEvent()
   * @generated
   */
	int TIME_EVENT = 28;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_EXTENSIONS = STATE_EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__ID = STATE_EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__SID = STATE_EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__CONSTRAINTS = STATE_EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_CONSTRAINTS = STATE_EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_MIGRATED_ELEMENTS = STATE_EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__NAME = STATE_EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__INCOMING_TRACES = STATE_EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OUTGOING_TRACES = STATE_EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__VISIBLE_IN_DOC = STATE_EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__VISIBLE_IN_LM = STATE_EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__SUMMARY = STATE_EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__DESCRIPTION = STATE_EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__REVIEW = STATE_EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_PROPERTY_VALUES = STATE_EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = STATE_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__APPLIED_PROPERTY_VALUES = STATE_EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_PROPERTY_VALUE_GROUPS = STATE_EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = STATE_EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__STATUS = STATE_EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__FEATURES = STATE_EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__ABSTRACT_TYPED_ELEMENTS = STATE_EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Expression</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__EXPRESSION = STATE_EVENT__EXPRESSION;

	/**
   * The feature id for the '<em><b>Owned State Event Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__OWNED_STATE_EVENT_REALIZATIONS = STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT__KIND = STATE_EVENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Time Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EVENT_FEATURE_COUNT = STATE_EVENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.TransitionKind <em>Transition Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.TransitionKind
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTransitionKind()
   * @generated
   */
	int TRANSITION_KIND = 29;


	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.TimeEventKind <em>Time Event Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.TimeEventKind
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTimeEventKind()
   * @generated
   */
	int TIME_EVENT_KIND = 30;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacommon.ChangeEventKind <em>Change Event Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEventKind
   * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getChangeEventKind()
   * @generated
   */
	int CHANGE_EVENT_KIND = 31;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg <em>Abstract Capability Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability Pkg</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg
   * @generated
   */
	EClass getAbstractCapabilityPkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.GenericTrace <em>Generic Trace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generic Trace</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.GenericTrace
   * @generated
   */
	EClass getGenericTrace();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.GenericTrace#getKeyValuePairs <em>Key Value Pairs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Key Value Pairs</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.GenericTrace#getKeyValuePairs()
   * @see #getGenericTrace()
   * @generated
   */
	EReference getGenericTrace_KeyValuePairs();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.GenericTrace#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.GenericTrace#getSource()
   * @see #getGenericTrace()
   * @generated
   */
	EReference getGenericTrace_Source();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.GenericTrace#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.GenericTrace#getTarget()
   * @see #getGenericTrace()
   * @generated
   */
	EReference getGenericTrace_Target();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.TransfoLink <em>Transfo Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Transfo Link</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.TransfoLink
   * @generated
   */
	EClass getTransfoLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.JustificationLink <em>Justification Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Justification Link</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.JustificationLink
   * @generated
   */
	EClass getJustificationLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement <em>Capability Realization Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Realization Involvement</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement
   * @generated
   */
	EClass getCapabilityRealizationInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement#getInvolvedCapabilityRealizationInvolvedElement <em>Involved Capability Realization Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Involved Capability Realization Involved Element</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement#getInvolvedCapabilityRealizationInvolvedElement()
   * @see #getCapabilityRealizationInvolvement()
   * @generated
   */
	EReference getCapabilityRealizationInvolvement_InvolvedCapabilityRealizationInvolvedElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement <em>Capability Realization Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Realization Involved Element</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement
   * @generated
   */
	EClass getCapabilityRealizationInvolvedElement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getCapabilityRealizationInvolvements <em>Capability Realization Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Capability Realization Involvements</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getCapabilityRealizationInvolvements()
   * @see #getCapabilityRealizationInvolvedElement()
   * @generated
   */
	EReference getCapabilityRealizationInvolvedElement_CapabilityRealizationInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement#getInvolvingCapabilityRealizations()
   * @see #getCapabilityRealizationInvolvedElement()
   * @generated
   */
	EReference getCapabilityRealizationInvolvedElement_InvolvingCapabilityRealizations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.StateMachine <em>State Machine</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State Machine</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateMachine
   * @generated
   */
	EClass getStateMachine();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.StateMachine#getOwnedRegions <em>Owned Regions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Regions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateMachine#getOwnedRegions()
   * @see #getStateMachine()
   * @generated
   */
	EReference getStateMachine_OwnedRegions();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.StateMachine#getOwnedConnectionPoints <em>Owned Connection Points</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Connection Points</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateMachine#getOwnedConnectionPoints()
   * @see #getStateMachine()
   * @generated
   */
	EReference getStateMachine_OwnedConnectionPoints();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.Region <em>Region</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Region</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.Region
   * @generated
   */
	EClass getRegion();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.Region#getOwnedStates <em>Owned States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned States</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.Region#getOwnedStates()
   * @see #getRegion()
   * @generated
   */
	EReference getRegion_OwnedStates();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.Region#getOwnedTransitions <em>Owned Transitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Transitions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.Region#getOwnedTransitions()
   * @see #getRegion()
   * @generated
   */
	EReference getRegion_OwnedTransitions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.Region#getInvolvedStates <em>Involved States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved States</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.Region#getInvolvedStates()
   * @see #getRegion()
   * @generated
   */
	EReference getRegion_InvolvedStates();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.State <em>State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State
   * @generated
   */
	EClass getState();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getOwnedRegions <em>Owned Regions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Regions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getOwnedRegions()
   * @see #getState()
   * @generated
   */
	EReference getState_OwnedRegions();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getOwnedConnectionPoints <em>Owned Connection Points</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Connection Points</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getOwnedConnectionPoints()
   * @see #getState()
   * @generated
   */
	EReference getState_OwnedConnectionPoints();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getAvailableAbstractFunctions <em>Available Abstract Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Available Abstract Functions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getAvailableAbstractFunctions()
   * @see #getState()
   * @generated
   */
	EReference getState_AvailableAbstractFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getAvailableFunctionalChains <em>Available Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Available Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getAvailableFunctionalChains()
   * @see #getState()
   * @generated
   */
	EReference getState_AvailableFunctionalChains();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getAvailableAbstractCapabilities <em>Available Abstract Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Available Abstract Capabilities</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getAvailableAbstractCapabilities()
   * @see #getState()
   * @generated
   */
	EReference getState_AvailableAbstractCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getEntry <em>Entry</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Entry</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getEntry()
   * @see #getState()
   * @generated
   */
	EReference getState_Entry();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getDoActivity <em>Do Activity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Do Activity</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getDoActivity()
   * @see #getState()
   * @generated
   */
	EReference getState_DoActivity();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.State#getExit <em>Exit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exit</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getExit()
   * @see #getState()
   * @generated
   */
	EReference getState_Exit();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.capellacommon.State#getStateInvariant <em>State Invariant</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>State Invariant</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.State#getStateInvariant()
   * @see #getState()
   * @generated
   */
	EReference getState_StateInvariant();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.Mode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mode</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.Mode
   * @generated
   */
	EClass getMode();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.FinalState <em>Final State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Final State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.FinalState
   * @generated
   */
	EClass getFinalState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.AbstractState <em>Abstract State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState
   * @generated
   */
	EClass getAbstractState();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.AbstractState#getOwnedAbstractStateRealizations <em>Owned Abstract State Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Abstract State Realizations</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState#getOwnedAbstractStateRealizations()
   * @see #getAbstractState()
   * @generated
   */
	EReference getAbstractState_OwnedAbstractStateRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.AbstractState#getRealizedAbstractStates <em>Realized Abstract States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Abstract States</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState#getRealizedAbstractStates()
   * @see #getAbstractState()
   * @generated
   */
	EReference getAbstractState_RealizedAbstractStates();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.AbstractState#getRealizingAbstractStates <em>Realizing Abstract States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Abstract States</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState#getRealizingAbstractStates()
   * @see #getAbstractState()
   * @generated
   */
	EReference getAbstractState_RealizingAbstractStates();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.AbstractState#getOutgoing <em>Outgoing</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState#getOutgoing()
   * @see #getAbstractState()
   * @generated
   */
	EReference getAbstractState_Outgoing();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.AbstractState#getIncoming <em>Incoming</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState#getIncoming()
   * @see #getAbstractState()
   * @generated
   */
	EReference getAbstractState_Incoming();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.AbstractState#getInvolverRegions <em>Involver Regions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involver Regions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractState#getInvolverRegions()
   * @see #getAbstractState()
   * @generated
   */
	EReference getAbstractState_InvolverRegions();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.StateTransition <em>State Transition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State Transition</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition
   * @generated
   */
	EClass getStateTransition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getGuard <em>Guard</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Guard</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getGuard()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_Guard();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getKind()
   * @see #getStateTransition()
   * @generated
   */
	EAttribute getStateTransition_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggerDescription <em>Trigger Description</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Trigger Description</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggerDescription()
   * @see #getStateTransition()
   * @generated
   */
	EAttribute getStateTransition_TriggerDescription();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getSource()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_Source();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getTarget()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_Target();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getEffect <em>Effect</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Effect</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getEffect()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_Effect();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggers <em>Triggers</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Triggers</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getTriggers()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_Triggers();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getOwnedStateTransitionRealizations <em>Owned State Transition Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Transition Realizations</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getOwnedStateTransitionRealizations()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_OwnedStateTransitionRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getRealizedStateTransitions <em>Realized State Transitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized State Transitions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getRealizedStateTransitions()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_RealizedStateTransitions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacommon.StateTransition#getRealizingStateTransitions <em>Realizing State Transitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing State Transitions</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransition#getRealizingStateTransitions()
   * @see #getStateTransition()
   * @generated
   */
	EReference getStateTransition_RealizingStateTransitions();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.Pseudostate <em>Pseudostate</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pseudostate</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.Pseudostate
   * @generated
   */
	EClass getPseudostate();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.InitialPseudoState <em>Initial Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Initial Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.InitialPseudoState
   * @generated
   */
	EClass getInitialPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.JoinPseudoState <em>Join Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Join Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.JoinPseudoState
   * @generated
   */
	EClass getJoinPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.ForkPseudoState <em>Fork Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fork Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ForkPseudoState
   * @generated
   */
	EClass getForkPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.ChoicePseudoState <em>Choice Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Choice Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ChoicePseudoState
   * @generated
   */
	EClass getChoicePseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.TerminatePseudoState <em>Terminate Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Terminate Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.TerminatePseudoState
   * @generated
   */
	EClass getTerminatePseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.AbstractStateRealization <em>Abstract State Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract State Realization</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractStateRealization
   * @generated
   */
	EClass getAbstractStateRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.AbstractStateRealization#getRealizedAbstractState <em>Realized Abstract State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Abstract State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractStateRealization#getRealizedAbstractState()
   * @see #getAbstractStateRealization()
   * @generated
   */
	EReference getAbstractStateRealization_RealizedAbstractState();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.AbstractStateRealization#getRealizingAbstractState <em>Realizing Abstract State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Abstract State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.AbstractStateRealization#getRealizingAbstractState()
   * @see #getAbstractStateRealization()
   * @generated
   */
	EReference getAbstractStateRealization_RealizingAbstractState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization <em>State Transition Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State Transition Realization</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransitionRealization
   * @generated
   */
	EClass getStateTransitionRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization#getRealizedStateTransition <em>Realized State Transition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized State Transition</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransitionRealization#getRealizedStateTransition()
   * @see #getStateTransitionRealization()
   * @generated
   */
	EReference getStateTransitionRealization_RealizedStateTransition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateTransitionRealization#getRealizingStateTransition <em>Realizing State Transition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing State Transition</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateTransitionRealization#getRealizingStateTransition()
   * @see #getStateTransitionRealization()
   * @generated
   */
	EReference getStateTransitionRealization_RealizingStateTransition();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState <em>Shallow History Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Shallow History Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState
   * @generated
   */
	EClass getShallowHistoryPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState <em>Deep History Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Deep History Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState
   * @generated
   */
	EClass getDeepHistoryPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState <em>Entry Point Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entry Point Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState
   * @generated
   */
	EClass getEntryPointPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState <em>Exit Point Pseudo State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exit Point Pseudo State</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState
   * @generated
   */
	EClass getExitPointPseudoState();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.StateEventRealization <em>State Event Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State Event Realization</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateEventRealization
   * @generated
   */
	EClass getStateEventRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateEventRealization#getRealizedEvent <em>Realized Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Event</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateEventRealization#getRealizedEvent()
   * @see #getStateEventRealization()
   * @generated
   */
	EReference getStateEventRealization_RealizedEvent();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateEventRealization#getRealizingEvent <em>Realizing Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Event</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateEventRealization#getRealizingEvent()
   * @see #getStateEventRealization()
   * @generated
   */
	EReference getStateEventRealization_RealizingEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.StateEvent <em>State Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State Event</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateEvent
   * @generated
   */
	EClass getStateEvent();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacommon.StateEvent#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Expression</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateEvent#getExpression()
   * @see #getStateEvent()
   * @generated
   */
	EReference getStateEvent_Expression();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacommon.StateEvent#getOwnedStateEventRealizations <em>Owned State Event Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Event Realizations</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.StateEvent#getOwnedStateEventRealizations()
   * @see #getStateEvent()
   * @generated
   */
	EReference getStateEvent_OwnedStateEventRealizations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.ChangeEvent <em>Change Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Change Event</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEvent
   * @generated
   */
	EClass getChangeEvent();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacommon.ChangeEvent#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEvent#getKind()
   * @see #getChangeEvent()
   * @generated
   */
	EAttribute getChangeEvent_Kind();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacommon.TimeEvent <em>Time Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Event</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.TimeEvent
   * @generated
   */
	EClass getTimeEvent();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacommon.TimeEvent#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.TimeEvent#getKind()
   * @see #getTimeEvent()
   * @generated
   */
	EAttribute getTimeEvent_Kind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.capellacommon.TransitionKind <em>Transition Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Transition Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.TransitionKind
   * @generated
   */
	EEnum getTransitionKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.capellacommon.TimeEventKind <em>Time Event Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Time Event Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.TimeEventKind
   * @generated
   */
	EEnum getTimeEventKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.capellacommon.ChangeEventKind <em>Change Event Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Change Event Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacommon.ChangeEventKind
   * @generated
   */
	EEnum getChangeEventKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	CapellacommonFactory getCapellacommonFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractCapabilityPkgImpl <em>Abstract Capability Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.AbstractCapabilityPkgImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getAbstractCapabilityPkg()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY_PKG = eINSTANCE.getAbstractCapabilityPkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.GenericTraceImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getGenericTrace()
     * @generated
     */
		EClass GENERIC_TRACE = eINSTANCE.getGenericTrace();

		/**
     * The meta object literal for the '<em><b>Key Value Pairs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERIC_TRACE__KEY_VALUE_PAIRS = eINSTANCE.getGenericTrace_KeyValuePairs();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERIC_TRACE__SOURCE = eINSTANCE.getGenericTrace_Source();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERIC_TRACE__TARGET = eINSTANCE.getGenericTrace_Target();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.TransfoLinkImpl <em>Transfo Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.TransfoLinkImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTransfoLink()
     * @generated
     */
		EClass TRANSFO_LINK = eINSTANCE.getTransfoLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.JustificationLinkImpl <em>Justification Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.JustificationLinkImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getJustificationLink()
     * @generated
     */
		EClass JUSTIFICATION_LINK = eINSTANCE.getJustificationLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl <em>Capability Realization Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getCapabilityRealizationInvolvement()
     * @generated
     */
		EClass CAPABILITY_REALIZATION_INVOLVEMENT = eINSTANCE.getCapabilityRealizationInvolvement();

		/**
     * The meta object literal for the '<em><b>Involved Capability Realization Involved Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT = eINSTANCE.getCapabilityRealizationInvolvement_InvolvedCapabilityRealizationInvolvedElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvedElementImpl <em>Capability Realization Involved Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvedElementImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getCapabilityRealizationInvolvedElement()
     * @generated
     */
		EClass CAPABILITY_REALIZATION_INVOLVED_ELEMENT = eINSTANCE.getCapabilityRealizationInvolvedElement();

		/**
     * The meta object literal for the '<em><b>Capability Realization Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS = eINSTANCE.getCapabilityRealizationInvolvedElement_CapabilityRealizationInvolvements();

		/**
     * The meta object literal for the '<em><b>Involving Capability Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS = eINSTANCE.getCapabilityRealizationInvolvedElement_InvolvingCapabilityRealizations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl <em>State Machine</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateMachine()
     * @generated
     */
		EClass STATE_MACHINE = eINSTANCE.getStateMachine();

		/**
     * The meta object literal for the '<em><b>Owned Regions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_MACHINE__OWNED_REGIONS = eINSTANCE.getStateMachine_OwnedRegions();

		/**
     * The meta object literal for the '<em><b>Owned Connection Points</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_MACHINE__OWNED_CONNECTION_POINTS = eINSTANCE.getStateMachine_OwnedConnectionPoints();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.RegionImpl <em>Region</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.RegionImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getRegion()
     * @generated
     */
		EClass REGION = eINSTANCE.getRegion();

		/**
     * The meta object literal for the '<em><b>Owned States</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REGION__OWNED_STATES = eINSTANCE.getRegion_OwnedStates();

		/**
     * The meta object literal for the '<em><b>Owned Transitions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REGION__OWNED_TRANSITIONS = eINSTANCE.getRegion_OwnedTransitions();

		/**
     * The meta object literal for the '<em><b>Involved States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REGION__INVOLVED_STATES = eINSTANCE.getRegion_InvolvedStates();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl <em>State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.StateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getState()
     * @generated
     */
		EClass STATE = eINSTANCE.getState();

		/**
     * The meta object literal for the '<em><b>Owned Regions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__OWNED_REGIONS = eINSTANCE.getState_OwnedRegions();

		/**
     * The meta object literal for the '<em><b>Owned Connection Points</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__OWNED_CONNECTION_POINTS = eINSTANCE.getState_OwnedConnectionPoints();

		/**
     * The meta object literal for the '<em><b>Available Abstract Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__AVAILABLE_ABSTRACT_FUNCTIONS = eINSTANCE.getState_AvailableAbstractFunctions();

		/**
     * The meta object literal for the '<em><b>Available Functional Chains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__AVAILABLE_FUNCTIONAL_CHAINS = eINSTANCE.getState_AvailableFunctionalChains();

		/**
     * The meta object literal for the '<em><b>Available Abstract Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__AVAILABLE_ABSTRACT_CAPABILITIES = eINSTANCE.getState_AvailableAbstractCapabilities();

		/**
     * The meta object literal for the '<em><b>Entry</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__ENTRY = eINSTANCE.getState_Entry();

		/**
     * The meta object literal for the '<em><b>Do Activity</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__DO_ACTIVITY = eINSTANCE.getState_DoActivity();

		/**
     * The meta object literal for the '<em><b>Exit</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__EXIT = eINSTANCE.getState_Exit();

		/**
     * The meta object literal for the '<em><b>State Invariant</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE__STATE_INVARIANT = eINSTANCE.getState_StateInvariant();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ModeImpl <em>Mode</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.ModeImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getMode()
     * @generated
     */
		EClass MODE = eINSTANCE.getMode();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.FinalStateImpl <em>Final State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.FinalStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getFinalState()
     * @generated
     */
		EClass FINAL_STATE = eINSTANCE.getFinalState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl <em>Abstract State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getAbstractState()
     * @generated
     */
		EClass ABSTRACT_STATE = eINSTANCE.getAbstractState();

		/**
     * The meta object literal for the '<em><b>Owned Abstract State Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS = eINSTANCE.getAbstractState_OwnedAbstractStateRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Abstract States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE__REALIZED_ABSTRACT_STATES = eINSTANCE.getAbstractState_RealizedAbstractStates();

		/**
     * The meta object literal for the '<em><b>Realizing Abstract States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE__REALIZING_ABSTRACT_STATES = eINSTANCE.getAbstractState_RealizingAbstractStates();

		/**
     * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE__OUTGOING = eINSTANCE.getAbstractState_Outgoing();

		/**
     * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE__INCOMING = eINSTANCE.getAbstractState_Incoming();

		/**
     * The meta object literal for the '<em><b>Involver Regions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE__INVOLVER_REGIONS = eINSTANCE.getAbstractState_InvolverRegions();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl <em>State Transition</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateTransition()
     * @generated
     */
		EClass STATE_TRANSITION = eINSTANCE.getStateTransition();

		/**
     * The meta object literal for the '<em><b>Guard</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__GUARD = eINSTANCE.getStateTransition_Guard();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute STATE_TRANSITION__KIND = eINSTANCE.getStateTransition_Kind();

		/**
     * The meta object literal for the '<em><b>Trigger Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute STATE_TRANSITION__TRIGGER_DESCRIPTION = eINSTANCE.getStateTransition_TriggerDescription();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__SOURCE = eINSTANCE.getStateTransition_Source();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__TARGET = eINSTANCE.getStateTransition_Target();

		/**
     * The meta object literal for the '<em><b>Effect</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__EFFECT = eINSTANCE.getStateTransition_Effect();

		/**
     * The meta object literal for the '<em><b>Triggers</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__TRIGGERS = eINSTANCE.getStateTransition_Triggers();

		/**
     * The meta object literal for the '<em><b>Owned State Transition Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS = eINSTANCE.getStateTransition_OwnedStateTransitionRealizations();

		/**
     * The meta object literal for the '<em><b>Realized State Transitions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__REALIZED_STATE_TRANSITIONS = eINSTANCE.getStateTransition_RealizedStateTransitions();

		/**
     * The meta object literal for the '<em><b>Realizing State Transitions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION__REALIZING_STATE_TRANSITIONS = eINSTANCE.getStateTransition_RealizingStateTransitions();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.PseudostateImpl <em>Pseudostate</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.PseudostateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getPseudostate()
     * @generated
     */
		EClass PSEUDOSTATE = eINSTANCE.getPseudostate();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.InitialPseudoStateImpl <em>Initial Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.InitialPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getInitialPseudoState()
     * @generated
     */
		EClass INITIAL_PSEUDO_STATE = eINSTANCE.getInitialPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.JoinPseudoStateImpl <em>Join Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.JoinPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getJoinPseudoState()
     * @generated
     */
		EClass JOIN_PSEUDO_STATE = eINSTANCE.getJoinPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ForkPseudoStateImpl <em>Fork Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.ForkPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getForkPseudoState()
     * @generated
     */
		EClass FORK_PSEUDO_STATE = eINSTANCE.getForkPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ChoicePseudoStateImpl <em>Choice Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.ChoicePseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getChoicePseudoState()
     * @generated
     */
		EClass CHOICE_PSEUDO_STATE = eINSTANCE.getChoicePseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.TerminatePseudoStateImpl <em>Terminate Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.TerminatePseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTerminatePseudoState()
     * @generated
     */
		EClass TERMINATE_PSEUDO_STATE = eINSTANCE.getTerminatePseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateRealizationImpl <em>Abstract State Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.AbstractStateRealizationImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getAbstractStateRealization()
     * @generated
     */
		EClass ABSTRACT_STATE_REALIZATION = eINSTANCE.getAbstractStateRealization();

		/**
     * The meta object literal for the '<em><b>Realized Abstract State</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE_REALIZATION__REALIZED_ABSTRACT_STATE = eINSTANCE.getAbstractStateRealization_RealizedAbstractState();

		/**
     * The meta object literal for the '<em><b>Realizing Abstract State</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STATE_REALIZATION__REALIZING_ABSTRACT_STATE = eINSTANCE.getAbstractStateRealization_RealizingAbstractState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionRealizationImpl <em>State Transition Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.StateTransitionRealizationImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateTransitionRealization()
     * @generated
     */
		EClass STATE_TRANSITION_REALIZATION = eINSTANCE.getStateTransitionRealization();

		/**
     * The meta object literal for the '<em><b>Realized State Transition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION_REALIZATION__REALIZED_STATE_TRANSITION = eINSTANCE.getStateTransitionRealization_RealizedStateTransition();

		/**
     * The meta object literal for the '<em><b>Realizing State Transition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_TRANSITION_REALIZATION__REALIZING_STATE_TRANSITION = eINSTANCE.getStateTransitionRealization_RealizingStateTransition();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ShallowHistoryPseudoStateImpl <em>Shallow History Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.ShallowHistoryPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getShallowHistoryPseudoState()
     * @generated
     */
		EClass SHALLOW_HISTORY_PSEUDO_STATE = eINSTANCE.getShallowHistoryPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.DeepHistoryPseudoStateImpl <em>Deep History Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.DeepHistoryPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getDeepHistoryPseudoState()
     * @generated
     */
		EClass DEEP_HISTORY_PSEUDO_STATE = eINSTANCE.getDeepHistoryPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.EntryPointPseudoStateImpl <em>Entry Point Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.EntryPointPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getEntryPointPseudoState()
     * @generated
     */
		EClass ENTRY_POINT_PSEUDO_STATE = eINSTANCE.getEntryPointPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ExitPointPseudoStateImpl <em>Exit Point Pseudo State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.ExitPointPseudoStateImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getExitPointPseudoState()
     * @generated
     */
		EClass EXIT_POINT_PSEUDO_STATE = eINSTANCE.getExitPointPseudoState();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateEventRealizationImpl <em>State Event Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.StateEventRealizationImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateEventRealization()
     * @generated
     */
		EClass STATE_EVENT_REALIZATION = eINSTANCE.getStateEventRealization();

		/**
     * The meta object literal for the '<em><b>Realized Event</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_EVENT_REALIZATION__REALIZED_EVENT = eINSTANCE.getStateEventRealization_RealizedEvent();

		/**
     * The meta object literal for the '<em><b>Realizing Event</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_EVENT_REALIZATION__REALIZING_EVENT = eINSTANCE.getStateEventRealization_RealizingEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.StateEventImpl <em>State Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.StateEventImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getStateEvent()
     * @generated
     */
		EClass STATE_EVENT = eINSTANCE.getStateEvent();

		/**
     * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_EVENT__EXPRESSION = eINSTANCE.getStateEvent_Expression();

		/**
     * The meta object literal for the '<em><b>Owned State Event Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS = eINSTANCE.getStateEvent_OwnedStateEventRealizations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.ChangeEventImpl <em>Change Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.ChangeEventImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getChangeEvent()
     * @generated
     */
		EClass CHANGE_EVENT = eINSTANCE.getChangeEvent();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CHANGE_EVENT__KIND = eINSTANCE.getChangeEvent_Kind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.impl.TimeEventImpl <em>Time Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.impl.TimeEventImpl
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTimeEvent()
     * @generated
     */
		EClass TIME_EVENT = eINSTANCE.getTimeEvent();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TIME_EVENT__KIND = eINSTANCE.getTimeEvent_Kind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.TransitionKind <em>Transition Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.TransitionKind
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTransitionKind()
     * @generated
     */
		EEnum TRANSITION_KIND = eINSTANCE.getTransitionKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.TimeEventKind <em>Time Event Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.TimeEventKind
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getTimeEventKind()
     * @generated
     */
		EEnum TIME_EVENT_KIND = eINSTANCE.getTimeEventKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacommon.ChangeEventKind <em>Change Event Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacommon.ChangeEventKind
     * @see org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl#getChangeEventKind()
     * @generated
     */
		EEnum CHANGE_EVENT_KIND = eINSTANCE.getChangeEventKind();

	}

} //CapellacommonPackage
