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
package org.polarsys.capella.core.data.pa.deployment;

import org.eclipse.emf.ecore.EClass;
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
 * @see org.polarsys.capella.core.data.pa.deployment.DeploymentFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='PhysicalArchitecture aims at defining the system\'s software, middleware and hardware architecture modelling language (close to the OMG\'s Platform Independent Model (PIM) in addition to OMG\'s Platform Model (PM)) using notions close to OMG\'s MARTE Resource concept. It adds the Deployment concern.\r\nThis concern aggregates a lot of concepts regarding the others. A re-engineering of this concern should make sense.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='physical' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface DeploymentPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "deployment"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/pa/deployment/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.pa.deployment"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	DeploymentPackage eINSTANCE = org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.AbstractPhysicalInstanceImpl <em>Abstract Physical Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.AbstractPhysicalInstanceImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getAbstractPhysicalInstance()
   * @generated
   */
	int ABSTRACT_PHYSICAL_INSTANCE = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The number of structural features of the '<em>Abstract Physical Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.ComponentInstanceImpl <em>Component Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.ComponentInstanceImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getComponentInstance()
   * @generated
   */
	int COMPONENT_INSTANCE = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__ID = ABSTRACT_PHYSICAL_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__SID = ABSTRACT_PHYSICAL_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__CONSTRAINTS = ABSTRACT_PHYSICAL_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__INCOMING_TRACES = ABSTRACT_PHYSICAL_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OUTGOING_TRACES = ABSTRACT_PHYSICAL_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__SUMMARY = ABSTRACT_PHYSICAL_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__DESCRIPTION = ABSTRACT_PHYSICAL_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__REVIEW = ABSTRACT_PHYSICAL_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__STATUS = ABSTRACT_PHYSICAL_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__FEATURES = ABSTRACT_PHYSICAL_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__NAME = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__DEPLOYING_LINKS = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__DEPLOYMENT_LINKS = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Port Instances</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__PORT_INSTANCES = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Abstract Physical Instances</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_ABSTRACT_PHYSICAL_INSTANCES = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Instance Deployment Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__OWNED_INSTANCE_DEPLOYMENT_LINKS = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE__TYPE = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 6;

	/**
   * The number of structural features of the '<em>Component Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_INSTANCE_FEATURE_COUNT = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 7;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.ConnectionInstanceImpl <em>Connection Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.ConnectionInstanceImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getConnectionInstance()
   * @generated
   */
	int CONNECTION_INSTANCE = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__ID = ABSTRACT_PHYSICAL_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__SID = ABSTRACT_PHYSICAL_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__CONSTRAINTS = ABSTRACT_PHYSICAL_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__INCOMING_TRACES = ABSTRACT_PHYSICAL_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OUTGOING_TRACES = ABSTRACT_PHYSICAL_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__SUMMARY = ABSTRACT_PHYSICAL_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__DESCRIPTION = ABSTRACT_PHYSICAL_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__REVIEW = ABSTRACT_PHYSICAL_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__STATUS = ABSTRACT_PHYSICAL_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__FEATURES = ABSTRACT_PHYSICAL_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Connection Ends</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__CONNECTION_ENDS = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE__TYPE = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Connection Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONNECTION_INSTANCE_FEATURE_COUNT = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.DeploymentAspectImpl <em>Aspect</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentAspectImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getDeploymentAspect()
   * @generated
   */
	int DEPLOYMENT_ASPECT = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Configurations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_CONFIGURATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Deployment Aspects</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT__OWNED_DEPLOYMENT_ASPECTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Aspect</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_ASPECT_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.DeploymentConfigurationImpl <em>Configuration</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentConfigurationImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getDeploymentConfiguration()
   * @generated
   */
	int DEPLOYMENT_CONFIGURATION = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Deployment Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Physical Instances</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Configuration</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_CONFIGURATION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.InstanceDeploymentLinkImpl <em>Instance Deployment Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.InstanceDeploymentLinkImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getInstanceDeploymentLink()
   * @generated
   */
	int INSTANCE_DEPLOYMENT_LINK = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OWNED_EXTENSIONS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__ID = CsPackage.ABSTRACT_DEPLOYMENT_LINK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__SID = CsPackage.ABSTRACT_DEPLOYMENT_LINK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__CONSTRAINTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OWNED_CONSTRAINTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__REALIZED_FLOW = CsPackage.ABSTRACT_DEPLOYMENT_LINK__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__INCOMING_TRACES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OUTGOING_TRACES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__VISIBLE_IN_DOC = CsPackage.ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__VISIBLE_IN_LM = CsPackage.ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__SUMMARY = CsPackage.ABSTRACT_DEPLOYMENT_LINK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__DESCRIPTION = CsPackage.ABSTRACT_DEPLOYMENT_LINK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__REVIEW = CsPackage.ABSTRACT_DEPLOYMENT_LINK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__STATUS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__FEATURES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__FEATURES;

	/**
   * The feature id for the '<em><b>Deployed Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__DEPLOYED_ELEMENT = CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT;

	/**
   * The feature id for the '<em><b>Location</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK__LOCATION = CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION;

	/**
   * The number of structural features of the '<em>Instance Deployment Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_DEPLOYMENT_LINK_FEATURE_COUNT = CsPackage.ABSTRACT_DEPLOYMENT_LINK_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.PartDeploymentLinkImpl <em>Part Deployment Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.PartDeploymentLinkImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getPartDeploymentLink()
   * @generated
   */
	int PART_DEPLOYMENT_LINK = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OWNED_EXTENSIONS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__ID = CsPackage.ABSTRACT_DEPLOYMENT_LINK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__SID = CsPackage.ABSTRACT_DEPLOYMENT_LINK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__CONSTRAINTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OWNED_CONSTRAINTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__REALIZED_FLOW = CsPackage.ABSTRACT_DEPLOYMENT_LINK__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__INCOMING_TRACES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OUTGOING_TRACES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__VISIBLE_IN_DOC = CsPackage.ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__VISIBLE_IN_LM = CsPackage.ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__SUMMARY = CsPackage.ABSTRACT_DEPLOYMENT_LINK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__DESCRIPTION = CsPackage.ABSTRACT_DEPLOYMENT_LINK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__REVIEW = CsPackage.ABSTRACT_DEPLOYMENT_LINK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__STATUS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__FEATURES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__FEATURES;

	/**
   * The feature id for the '<em><b>Deployed Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__DEPLOYED_ELEMENT = CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT;

	/**
   * The feature id for the '<em><b>Location</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK__LOCATION = CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION;

	/**
   * The number of structural features of the '<em>Part Deployment Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_DEPLOYMENT_LINK_FEATURE_COUNT = CsPackage.ABSTRACT_DEPLOYMENT_LINK_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl <em>Port Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getPortInstance()
   * @generated
   */
	int PORT_INSTANCE = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__ID = ABSTRACT_PHYSICAL_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__SID = ABSTRACT_PHYSICAL_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__CONSTRAINTS = ABSTRACT_PHYSICAL_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__INCOMING_TRACES = ABSTRACT_PHYSICAL_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OUTGOING_TRACES = ABSTRACT_PHYSICAL_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__SUMMARY = ABSTRACT_PHYSICAL_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__DESCRIPTION = ABSTRACT_PHYSICAL_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__REVIEW = ABSTRACT_PHYSICAL_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__STATUS = ABSTRACT_PHYSICAL_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__FEATURES = ABSTRACT_PHYSICAL_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Connections</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__CONNECTIONS = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__COMPONENT = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE__TYPE = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Port Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_INSTANCE_FEATURE_COUNT = ABSTRACT_PHYSICAL_INSTANCE_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.TypeDeploymentLinkImpl <em>Type Deployment Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.pa.deployment.impl.TypeDeploymentLinkImpl
   * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getTypeDeploymentLink()
   * @generated
   */
	int TYPE_DEPLOYMENT_LINK = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OWNED_EXTENSIONS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__ID = CsPackage.ABSTRACT_DEPLOYMENT_LINK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__SID = CsPackage.ABSTRACT_DEPLOYMENT_LINK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__CONSTRAINTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OWNED_CONSTRAINTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__REALIZED_FLOW = CsPackage.ABSTRACT_DEPLOYMENT_LINK__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__INCOMING_TRACES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OUTGOING_TRACES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__VISIBLE_IN_DOC = CsPackage.ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__VISIBLE_IN_LM = CsPackage.ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__SUMMARY = CsPackage.ABSTRACT_DEPLOYMENT_LINK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__DESCRIPTION = CsPackage.ABSTRACT_DEPLOYMENT_LINK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__REVIEW = CsPackage.ABSTRACT_DEPLOYMENT_LINK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__STATUS = CsPackage.ABSTRACT_DEPLOYMENT_LINK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__FEATURES = CsPackage.ABSTRACT_DEPLOYMENT_LINK__FEATURES;

	/**
   * The feature id for the '<em><b>Deployed Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__DEPLOYED_ELEMENT = CsPackage.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT;

	/**
   * The feature id for the '<em><b>Location</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK__LOCATION = CsPackage.ABSTRACT_DEPLOYMENT_LINK__LOCATION;

	/**
   * The number of structural features of the '<em>Type Deployment Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_DEPLOYMENT_LINK_FEATURE_COUNT = CsPackage.ABSTRACT_DEPLOYMENT_LINK_FEATURE_COUNT + 0;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance <em>Component Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Instance</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ComponentInstance
   * @generated
   */
	EClass getComponentInstance();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getPortInstances <em>Port Instances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Port Instances</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getPortInstances()
   * @see #getComponentInstance()
   * @generated
   */
	EReference getComponentInstance_PortInstances();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getOwnedAbstractPhysicalInstances <em>Owned Abstract Physical Instances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Abstract Physical Instances</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getOwnedAbstractPhysicalInstances()
   * @see #getComponentInstance()
   * @generated
   */
	EReference getComponentInstance_OwnedAbstractPhysicalInstances();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getOwnedInstanceDeploymentLinks <em>Owned Instance Deployment Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Instance Deployment Links</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getOwnedInstanceDeploymentLinks()
   * @see #getComponentInstance()
   * @generated
   */
	EReference getComponentInstance_OwnedInstanceDeploymentLinks();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ComponentInstance#getType()
   * @see #getComponentInstance()
   * @generated
   */
	EReference getComponentInstance_Type();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance <em>Connection Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Connection Instance</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ConnectionInstance
   * @generated
   */
	EClass getConnectionInstance();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getConnectionEnds <em>Connection Ends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Connection Ends</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getConnectionEnds()
   * @see #getConnectionInstance()
   * @generated
   */
	EReference getConnectionInstance_ConnectionEnds();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.ConnectionInstance#getType()
   * @see #getConnectionInstance()
   * @generated
   */
	EReference getConnectionInstance_Type();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.DeploymentAspect <em>Aspect</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Aspect</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentAspect
   * @generated
   */
	EClass getDeploymentAspect();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.deployment.DeploymentAspect#getOwnedConfigurations <em>Owned Configurations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Configurations</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentAspect#getOwnedConfigurations()
   * @see #getDeploymentAspect()
   * @generated
   */
	EReference getDeploymentAspect_OwnedConfigurations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.deployment.DeploymentAspect#getOwnedDeploymentAspects <em>Owned Deployment Aspects</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Deployment Aspects</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentAspect#getOwnedDeploymentAspects()
   * @see #getDeploymentAspect()
   * @generated
   */
	EReference getDeploymentAspect_OwnedDeploymentAspects();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration <em>Configuration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration
   * @generated
   */
	EClass getDeploymentConfiguration();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Deployment Links</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration#getOwnedDeploymentLinks()
   * @see #getDeploymentConfiguration()
   * @generated
   */
	EReference getDeploymentConfiguration_OwnedDeploymentLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration#getOwnedPhysicalInstances <em>Owned Physical Instances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Instances</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration#getOwnedPhysicalInstances()
   * @see #getDeploymentConfiguration()
   * @generated
   */
	EReference getDeploymentConfiguration_OwnedPhysicalInstances();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.InstanceDeploymentLink <em>Instance Deployment Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Instance Deployment Link</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.InstanceDeploymentLink
   * @generated
   */
	EClass getInstanceDeploymentLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink <em>Part Deployment Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Part Deployment Link</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink
   * @generated
   */
	EClass getPartDeploymentLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance <em>Abstract Physical Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Physical Instance</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance
   * @generated
   */
	EClass getAbstractPhysicalInstance();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.PortInstance <em>Port Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port Instance</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.PortInstance
   * @generated
   */
	EClass getPortInstance();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getConnections <em>Connections</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Connections</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.PortInstance#getConnections()
   * @see #getPortInstance()
   * @generated
   */
	EReference getPortInstance_Connections();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getComponent <em>Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Component</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.PortInstance#getComponent()
   * @see #getPortInstance()
   * @generated
   */
	EReference getPortInstance_Component();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.pa.deployment.PortInstance#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.PortInstance#getType()
   * @see #getPortInstance()
   * @generated
   */
	EReference getPortInstance_Type();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink <em>Type Deployment Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Deployment Link</em>'.
   * @see org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink
   * @generated
   */
	EClass getTypeDeploymentLink();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	DeploymentFactory getDeploymentFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.ComponentInstanceImpl <em>Component Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.ComponentInstanceImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getComponentInstance()
     * @generated
     */
		EClass COMPONENT_INSTANCE = eINSTANCE.getComponentInstance();

		/**
     * The meta object literal for the '<em><b>Port Instances</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_INSTANCE__PORT_INSTANCES = eINSTANCE.getComponentInstance_PortInstances();

		/**
     * The meta object literal for the '<em><b>Owned Abstract Physical Instances</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_INSTANCE__OWNED_ABSTRACT_PHYSICAL_INSTANCES = eINSTANCE.getComponentInstance_OwnedAbstractPhysicalInstances();

		/**
     * The meta object literal for the '<em><b>Owned Instance Deployment Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_INSTANCE__OWNED_INSTANCE_DEPLOYMENT_LINKS = eINSTANCE.getComponentInstance_OwnedInstanceDeploymentLinks();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_INSTANCE__TYPE = eINSTANCE.getComponentInstance_Type();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.ConnectionInstanceImpl <em>Connection Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.ConnectionInstanceImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getConnectionInstance()
     * @generated
     */
		EClass CONNECTION_INSTANCE = eINSTANCE.getConnectionInstance();

		/**
     * The meta object literal for the '<em><b>Connection Ends</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONNECTION_INSTANCE__CONNECTION_ENDS = eINSTANCE.getConnectionInstance_ConnectionEnds();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONNECTION_INSTANCE__TYPE = eINSTANCE.getConnectionInstance_Type();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.DeploymentAspectImpl <em>Aspect</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentAspectImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getDeploymentAspect()
     * @generated
     */
		EClass DEPLOYMENT_ASPECT = eINSTANCE.getDeploymentAspect();

		/**
     * The meta object literal for the '<em><b>Owned Configurations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEPLOYMENT_ASPECT__OWNED_CONFIGURATIONS = eINSTANCE.getDeploymentAspect_OwnedConfigurations();

		/**
     * The meta object literal for the '<em><b>Owned Deployment Aspects</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEPLOYMENT_ASPECT__OWNED_DEPLOYMENT_ASPECTS = eINSTANCE.getDeploymentAspect_OwnedDeploymentAspects();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.DeploymentConfigurationImpl <em>Configuration</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentConfigurationImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getDeploymentConfiguration()
     * @generated
     */
		EClass DEPLOYMENT_CONFIGURATION = eINSTANCE.getDeploymentConfiguration();

		/**
     * The meta object literal for the '<em><b>Owned Deployment Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS = eINSTANCE.getDeploymentConfiguration_OwnedDeploymentLinks();

		/**
     * The meta object literal for the '<em><b>Owned Physical Instances</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEPLOYMENT_CONFIGURATION__OWNED_PHYSICAL_INSTANCES = eINSTANCE.getDeploymentConfiguration_OwnedPhysicalInstances();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.InstanceDeploymentLinkImpl <em>Instance Deployment Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.InstanceDeploymentLinkImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getInstanceDeploymentLink()
     * @generated
     */
		EClass INSTANCE_DEPLOYMENT_LINK = eINSTANCE.getInstanceDeploymentLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.PartDeploymentLinkImpl <em>Part Deployment Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.PartDeploymentLinkImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getPartDeploymentLink()
     * @generated
     */
		EClass PART_DEPLOYMENT_LINK = eINSTANCE.getPartDeploymentLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.AbstractPhysicalInstanceImpl <em>Abstract Physical Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.AbstractPhysicalInstanceImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getAbstractPhysicalInstance()
     * @generated
     */
		EClass ABSTRACT_PHYSICAL_INSTANCE = eINSTANCE.getAbstractPhysicalInstance();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl <em>Port Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.PortInstanceImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getPortInstance()
     * @generated
     */
		EClass PORT_INSTANCE = eINSTANCE.getPortInstance();

		/**
     * The meta object literal for the '<em><b>Connections</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_INSTANCE__CONNECTIONS = eINSTANCE.getPortInstance_Connections();

		/**
     * The meta object literal for the '<em><b>Component</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_INSTANCE__COMPONENT = eINSTANCE.getPortInstance_Component();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_INSTANCE__TYPE = eINSTANCE.getPortInstance_Type();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.pa.deployment.impl.TypeDeploymentLinkImpl <em>Type Deployment Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.pa.deployment.impl.TypeDeploymentLinkImpl
     * @see org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl#getTypeDeploymentLink()
     * @generated
     */
		EClass TYPE_DEPLOYMENT_LINK = eINSTANCE.getTypeDeploymentLink();

	}

} //DeploymentPackage
