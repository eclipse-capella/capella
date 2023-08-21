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
package org.polarsys.capella.core.data.capellamodeller;

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
 * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping profileName='Capella'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='CapellaModeller aims at defining project level concepts.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model CapellaCore.ecore' comment/notes='none' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CapellamodellerPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "capellamodeller"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/modeller/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.capellamodeller"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CapellamodellerPackage eINSTANCE = org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.ProjectImpl <em>Project</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellamodeller.impl.ProjectImpl
   * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getProject()
   * @generated
   */
	int PROJECT = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Key Value Pairs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__KEY_VALUE_PAIRS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Folders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_FOLDERS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Model Roots</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT__OWNED_MODEL_ROOTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Project</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROJECT_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.FolderImpl <em>Folder</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellamodeller.impl.FolderImpl
   * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getFolder()
   * @generated
   */
	int FOLDER = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Folders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_FOLDERS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Model Roots</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER__OWNED_MODEL_ROOTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Folder</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FOLDER_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellamodeller.ModelRoot <em>Model Root</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellamodeller.ModelRoot
   * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getModelRoot()
   * @generated
   */
	int MODEL_ROOT = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The number of structural features of the '<em>Model Root</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ROOT_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl <em>System Engineering</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl
   * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getSystemEngineering()
   * @generated
   */
	int SYSTEM_ENGINEERING = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_EXTENSIONS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__ID = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__SID = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONSTRAINTS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_CONSTRAINTS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__NAME = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__INCOMING_TRACES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OUTGOING_TRACES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__VISIBLE_IN_DOC = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__VISIBLE_IN_LM = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__SUMMARY = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__DESCRIPTION = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__REVIEW = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_PROPERTY_VALUES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__APPLIED_PROPERTY_VALUES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__STATUS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__FEATURES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_TRACES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_GENERIC_TRACES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__NAMING_RULES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Reuse Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__REUSE_LINKS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__REUSE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Reuse Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_REUSE_LINKS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_REUSE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Architectures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_ARCHITECTURES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>Owned Architecture Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__OWNED_ARCHITECTURE_PKGS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURE_PKGS;

	/**
   * The feature id for the '<em><b>Contained Operational Analysis</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Contained System Analysis</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Contained Logical Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Contained Physical Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Contained EPBS Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Contained Shared Pkgs</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>System Engineering</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_FEATURE_COUNT = CapellacorePackage.ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringPkgImpl <em>System Engineering Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringPkgImpl
   * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getSystemEngineeringPkg()
   * @generated
   */
	int SYSTEM_ENGINEERING_PKG = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned System Engineerings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG__OWNED_SYSTEM_ENGINEERINGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>System Engineering Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ENGINEERING_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.LibraryImpl <em>Library</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellamodeller.impl.LibraryImpl
   * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getLibrary()
   * @generated
   */
	int LIBRARY = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_EXTENSIONS = PROJECT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__ID = PROJECT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__SID = PROJECT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__CONSTRAINTS = PROJECT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_CONSTRAINTS = PROJECT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_MIGRATED_ELEMENTS = PROJECT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__NAME = PROJECT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__INCOMING_TRACES = PROJECT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OUTGOING_TRACES = PROJECT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__VISIBLE_IN_DOC = PROJECT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__VISIBLE_IN_LM = PROJECT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__SUMMARY = PROJECT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__DESCRIPTION = PROJECT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__REVIEW = PROJECT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_PROPERTY_VALUES = PROJECT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_ENUMERATION_PROPERTY_TYPES = PROJECT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__APPLIED_PROPERTY_VALUES = PROJECT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_PROPERTY_VALUE_GROUPS = PROJECT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__APPLIED_PROPERTY_VALUE_GROUPS = PROJECT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__STATUS = PROJECT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__FEATURES = PROJECT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_TRACES = PROJECT__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__CONTAINED_GENERIC_TRACES = PROJECT__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__NAMING_RULES = PROJECT__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_PROPERTY_VALUE_PKGS = PROJECT__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Key Value Pairs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__KEY_VALUE_PAIRS = PROJECT__KEY_VALUE_PAIRS;

	/**
   * The feature id for the '<em><b>Owned Folders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_FOLDERS = PROJECT__OWNED_FOLDERS;

	/**
   * The feature id for the '<em><b>Owned Model Roots</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY__OWNED_MODEL_ROOTS = PROJECT__OWNED_MODEL_ROOTS;

	/**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIBRARY_FEATURE_COUNT = PROJECT_FEATURE_COUNT + 0;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellamodeller.Project <em>Project</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Project</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Project
   * @generated
   */
	EClass getProject();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellamodeller.Project#getKeyValuePairs <em>Key Value Pairs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Key Value Pairs</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Project#getKeyValuePairs()
   * @see #getProject()
   * @generated
   */
	EReference getProject_KeyValuePairs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellamodeller.Project#getOwnedFolders <em>Owned Folders</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Folders</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Project#getOwnedFolders()
   * @see #getProject()
   * @generated
   */
	EReference getProject_OwnedFolders();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellamodeller.Project#getOwnedModelRoots <em>Owned Model Roots</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Model Roots</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Project#getOwnedModelRoots()
   * @see #getProject()
   * @generated
   */
	EReference getProject_OwnedModelRoots();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellamodeller.Folder <em>Folder</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Folder</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Folder
   * @generated
   */
	EClass getFolder();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellamodeller.Folder#getOwnedFolders <em>Owned Folders</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Folders</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Folder#getOwnedFolders()
   * @see #getFolder()
   * @generated
   */
	EReference getFolder_OwnedFolders();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellamodeller.Folder#getOwnedModelRoots <em>Owned Model Roots</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Model Roots</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Folder#getOwnedModelRoots()
   * @see #getFolder()
   * @generated
   */
	EReference getFolder_OwnedModelRoots();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellamodeller.ModelRoot <em>Model Root</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Root</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.ModelRoot
   * @generated
   */
	EClass getModelRoot();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering <em>System Engineering</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>System Engineering</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering
   * @generated
   */
	EClass getSystemEngineering();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedOperationalAnalysis <em>Contained Operational Analysis</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Operational Analysis</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedOperationalAnalysis()
   * @see #getSystemEngineering()
   * @generated
   */
	EReference getSystemEngineering_ContainedOperationalAnalysis();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedSystemAnalysis <em>Contained System Analysis</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained System Analysis</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedSystemAnalysis()
   * @see #getSystemEngineering()
   * @generated
   */
	EReference getSystemEngineering_ContainedSystemAnalysis();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedLogicalArchitectures <em>Contained Logical Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Logical Architectures</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedLogicalArchitectures()
   * @see #getSystemEngineering()
   * @generated
   */
	EReference getSystemEngineering_ContainedLogicalArchitectures();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedPhysicalArchitectures <em>Contained Physical Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Physical Architectures</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedPhysicalArchitectures()
   * @see #getSystemEngineering()
   * @generated
   */
	EReference getSystemEngineering_ContainedPhysicalArchitectures();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedEPBSArchitectures <em>Contained EPBS Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained EPBS Architectures</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedEPBSArchitectures()
   * @see #getSystemEngineering()
   * @generated
   */
	EReference getSystemEngineering_ContainedEPBSArchitectures();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedSharedPkgs <em>Contained Shared Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Shared Pkgs</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedSharedPkgs()
   * @see #getSystemEngineering()
   * @generated
   */
	EReference getSystemEngineering_ContainedSharedPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg <em>System Engineering Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>System Engineering Pkg</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg
   * @generated
   */
	EClass getSystemEngineeringPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg#getOwnedSystemEngineerings <em>Owned System Engineerings</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned System Engineerings</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg#getOwnedSystemEngineerings()
   * @see #getSystemEngineeringPkg()
   * @generated
   */
	EReference getSystemEngineeringPkg_OwnedSystemEngineerings();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellamodeller.Library <em>Library</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library</em>'.
   * @see org.polarsys.capella.core.data.capellamodeller.Library
   * @generated
   */
	EClass getLibrary();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	CapellamodellerFactory getCapellamodellerFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.ProjectImpl <em>Project</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellamodeller.impl.ProjectImpl
     * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getProject()
     * @generated
     */
		EClass PROJECT = eINSTANCE.getProject();

		/**
     * The meta object literal for the '<em><b>Key Value Pairs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROJECT__KEY_VALUE_PAIRS = eINSTANCE.getProject_KeyValuePairs();

		/**
     * The meta object literal for the '<em><b>Owned Folders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROJECT__OWNED_FOLDERS = eINSTANCE.getProject_OwnedFolders();

		/**
     * The meta object literal for the '<em><b>Owned Model Roots</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROJECT__OWNED_MODEL_ROOTS = eINSTANCE.getProject_OwnedModelRoots();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.FolderImpl <em>Folder</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellamodeller.impl.FolderImpl
     * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getFolder()
     * @generated
     */
		EClass FOLDER = eINSTANCE.getFolder();

		/**
     * The meta object literal for the '<em><b>Owned Folders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FOLDER__OWNED_FOLDERS = eINSTANCE.getFolder_OwnedFolders();

		/**
     * The meta object literal for the '<em><b>Owned Model Roots</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FOLDER__OWNED_MODEL_ROOTS = eINSTANCE.getFolder_OwnedModelRoots();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellamodeller.ModelRoot <em>Model Root</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellamodeller.ModelRoot
     * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getModelRoot()
     * @generated
     */
		EClass MODEL_ROOT = eINSTANCE.getModelRoot();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl <em>System Engineering</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl
     * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getSystemEngineering()
     * @generated
     */
		EClass SYSTEM_ENGINEERING = eINSTANCE.getSystemEngineering();

		/**
     * The meta object literal for the '<em><b>Contained Operational Analysis</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS = eINSTANCE.getSystemEngineering_ContainedOperationalAnalysis();

		/**
     * The meta object literal for the '<em><b>Contained System Analysis</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS = eINSTANCE.getSystemEngineering_ContainedSystemAnalysis();

		/**
     * The meta object literal for the '<em><b>Contained Logical Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES = eINSTANCE.getSystemEngineering_ContainedLogicalArchitectures();

		/**
     * The meta object literal for the '<em><b>Contained Physical Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES = eINSTANCE.getSystemEngineering_ContainedPhysicalArchitectures();

		/**
     * The meta object literal for the '<em><b>Contained EPBS Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES = eINSTANCE.getSystemEngineering_ContainedEPBSArchitectures();

		/**
     * The meta object literal for the '<em><b>Contained Shared Pkgs</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS = eINSTANCE.getSystemEngineering_ContainedSharedPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringPkgImpl <em>System Engineering Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringPkgImpl
     * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getSystemEngineeringPkg()
     * @generated
     */
		EClass SYSTEM_ENGINEERING_PKG = eINSTANCE.getSystemEngineeringPkg();

		/**
     * The meta object literal for the '<em><b>Owned System Engineerings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ENGINEERING_PKG__OWNED_SYSTEM_ENGINEERINGS = eINSTANCE.getSystemEngineeringPkg_OwnedSystemEngineerings();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellamodeller.impl.LibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellamodeller.impl.LibraryImpl
     * @see org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl#getLibrary()
     * @generated
     */
		EClass LIBRARY = eINSTANCE.getLibrary();

	}

} //CapellamodellerPackage
