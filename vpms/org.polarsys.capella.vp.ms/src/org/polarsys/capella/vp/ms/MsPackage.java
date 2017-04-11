/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/

package org.polarsys.capella.vp.ms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.MsFactory
 * @model kind="package"
 * @generated
 */
public interface MsPackage extends EPackage {
  /**
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNAME = "ms";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_URI = "http://www.polarsys.org/capella/ms";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_PREFIX = "ms";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  MsPackage eINSTANCE = org.polarsys.capella.vp.ms.impl.MsPackageImpl.init();

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl <em>CS Configuration</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.CSConfigurationImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getCSConfiguration()
   * @generated
   */
  int CS_CONFIGURATION = 0;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Supported Modes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__SUPPORTED_MODES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__ELEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Deployment Links</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__DEPLOYMENT_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Functions</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__FUNCTIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Functional Chains</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__FUNCTIONAL_CHAINS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Components</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__COMPONENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Ports</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__PORTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Child Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__CHILD_CONFIGURATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__KIND = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Access</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__ACCESS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Selector</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__SELECTOR = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Context</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__CONTEXT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 11;

  /**
   * The feature id for the '<em><b>Compare To</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION__COMPARE_TO = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 12;

  /**
   * The number of structural features of the '<em>CS Configuration</em>' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CS_CONFIGURATION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 13;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.FSMTypeImpl <em>FSM Type</em>}' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.FSMTypeImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getFSMType()
   * @generated
   */
  int FSM_TYPE = 1;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Ms</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE__MS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>FSM Type</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FSM_TYPE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.SituationImpl <em>Situation</em>}' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.SituationImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getSituation()
   * @generated
   */
  int SITUATION = 2;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION__EXPRESSION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Situation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int SITUATION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.BooleanExpressionImpl <em>Boolean
   * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.BooleanExpressionImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getBooleanExpression()
   * @generated
   */
  int BOOLEAN_EXPRESSION = 3;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__APPLIED_REQUIREMENTS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_REQUIREMENTS;

  /**
   * The number of structural features of the '<em>Boolean Expression</em>' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.BooleanOperationImpl <em>Boolean
   * Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.BooleanOperationImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getBooleanOperation()
   * @generated
   */
  int BOOLEAN_OPERATION = 4;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OWNED_EXTENSIONS = BOOLEAN_EXPRESSION__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__ID = BOOLEAN_EXPRESSION__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__SID = BOOLEAN_EXPRESSION__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__CONSTRAINTS = BOOLEAN_EXPRESSION__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OWNED_CONSTRAINTS = BOOLEAN_EXPRESSION__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__INCOMING_TRACES = BOOLEAN_EXPRESSION__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OUTGOING_TRACES = BOOLEAN_EXPRESSION__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__VISIBLE_IN_DOC = BOOLEAN_EXPRESSION__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__VISIBLE_IN_LM = BOOLEAN_EXPRESSION__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__SUMMARY = BOOLEAN_EXPRESSION__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__DESCRIPTION = BOOLEAN_EXPRESSION__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__REVIEW = BOOLEAN_EXPRESSION__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OWNED_PROPERTY_VALUES = BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = BOOLEAN_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUES = BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__STATUS = BOOLEAN_EXPRESSION__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__FEATURES = BOOLEAN_EXPRESSION__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__APPLIED_REQUIREMENTS = BOOLEAN_EXPRESSION__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__CHILDREN = BOOLEAN_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Operation</em>' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION_FEATURE_COUNT = BOOLEAN_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.InStateExpressionImpl <em>In State
   * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.InStateExpressionImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getInStateExpression()
   * @generated
   */
  int IN_STATE_EXPRESSION = 5;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__OWNED_EXTENSIONS = BOOLEAN_EXPRESSION__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__ID = BOOLEAN_EXPRESSION__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__SID = BOOLEAN_EXPRESSION__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__CONSTRAINTS = BOOLEAN_EXPRESSION__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__OWNED_CONSTRAINTS = BOOLEAN_EXPRESSION__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__INCOMING_TRACES = BOOLEAN_EXPRESSION__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__OUTGOING_TRACES = BOOLEAN_EXPRESSION__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__VISIBLE_IN_DOC = BOOLEAN_EXPRESSION__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__VISIBLE_IN_LM = BOOLEAN_EXPRESSION__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__SUMMARY = BOOLEAN_EXPRESSION__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__DESCRIPTION = BOOLEAN_EXPRESSION__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__REVIEW = BOOLEAN_EXPRESSION__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__OWNED_PROPERTY_VALUES = BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES = BOOLEAN_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__APPLIED_PROPERTY_VALUES = BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS = BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS = BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__STATUS = BOOLEAN_EXPRESSION__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__FEATURES = BOOLEAN_EXPRESSION__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__APPLIED_REQUIREMENTS = BOOLEAN_EXPRESSION__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>State</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION__STATE = BOOLEAN_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>In State Expression</em>' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_STATE_EXPRESSION_FEATURE_COUNT = BOOLEAN_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.InSituationExpressionImpl <em>In Situation
   * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.InSituationExpressionImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getInSituationExpression()
   * @generated
   */
  int IN_SITUATION_EXPRESSION = 6;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__OWNED_EXTENSIONS = BOOLEAN_EXPRESSION__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__ID = BOOLEAN_EXPRESSION__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__SID = BOOLEAN_EXPRESSION__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__CONSTRAINTS = BOOLEAN_EXPRESSION__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__OWNED_CONSTRAINTS = BOOLEAN_EXPRESSION__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__INCOMING_TRACES = BOOLEAN_EXPRESSION__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__OUTGOING_TRACES = BOOLEAN_EXPRESSION__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__VISIBLE_IN_DOC = BOOLEAN_EXPRESSION__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__VISIBLE_IN_LM = BOOLEAN_EXPRESSION__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__SUMMARY = BOOLEAN_EXPRESSION__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__DESCRIPTION = BOOLEAN_EXPRESSION__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__REVIEW = BOOLEAN_EXPRESSION__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__OWNED_PROPERTY_VALUES = BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES = BOOLEAN_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__APPLIED_PROPERTY_VALUES = BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS = BOOLEAN_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS = BOOLEAN_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__STATUS = BOOLEAN_EXPRESSION__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__FEATURES = BOOLEAN_EXPRESSION__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__APPLIED_REQUIREMENTS = BOOLEAN_EXPRESSION__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Situation</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION__SITUATION = BOOLEAN_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>In Situation Expression</em>' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int IN_SITUATION_EXPRESSION_FEATURE_COUNT = BOOLEAN_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.AndOperationImpl <em>And Operation</em>}' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.AndOperationImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getAndOperation()
   * @generated
   */
  int AND_OPERATION = 7;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__OWNED_EXTENSIONS = BOOLEAN_OPERATION__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__ID = BOOLEAN_OPERATION__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__SID = BOOLEAN_OPERATION__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__CONSTRAINTS = BOOLEAN_OPERATION__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__OWNED_CONSTRAINTS = BOOLEAN_OPERATION__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__INCOMING_TRACES = BOOLEAN_OPERATION__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__OUTGOING_TRACES = BOOLEAN_OPERATION__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__VISIBLE_IN_DOC = BOOLEAN_OPERATION__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__VISIBLE_IN_LM = BOOLEAN_OPERATION__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__SUMMARY = BOOLEAN_OPERATION__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__DESCRIPTION = BOOLEAN_OPERATION__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__REVIEW = BOOLEAN_OPERATION__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__OWNED_PROPERTY_VALUES = BOOLEAN_OPERATION__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = BOOLEAN_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__APPLIED_PROPERTY_VALUES = BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = BOOLEAN_OPERATION__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__STATUS = BOOLEAN_OPERATION__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__FEATURES = BOOLEAN_OPERATION__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__APPLIED_REQUIREMENTS = BOOLEAN_OPERATION__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION__CHILDREN = BOOLEAN_OPERATION__CHILDREN;

  /**
   * The number of structural features of the '<em>And Operation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int AND_OPERATION_FEATURE_COUNT = BOOLEAN_OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.OrOperationImpl <em>Or Operation</em>}' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.OrOperationImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getOrOperation()
   * @generated
   */
  int OR_OPERATION = 8;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__OWNED_EXTENSIONS = BOOLEAN_OPERATION__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__ID = BOOLEAN_OPERATION__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__SID = BOOLEAN_OPERATION__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__CONSTRAINTS = BOOLEAN_OPERATION__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__OWNED_CONSTRAINTS = BOOLEAN_OPERATION__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__INCOMING_TRACES = BOOLEAN_OPERATION__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__OUTGOING_TRACES = BOOLEAN_OPERATION__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__VISIBLE_IN_DOC = BOOLEAN_OPERATION__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__VISIBLE_IN_LM = BOOLEAN_OPERATION__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__SUMMARY = BOOLEAN_OPERATION__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__DESCRIPTION = BOOLEAN_OPERATION__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__REVIEW = BOOLEAN_OPERATION__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__OWNED_PROPERTY_VALUES = BOOLEAN_OPERATION__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = BOOLEAN_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__APPLIED_PROPERTY_VALUES = BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = BOOLEAN_OPERATION__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__STATUS = BOOLEAN_OPERATION__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__FEATURES = BOOLEAN_OPERATION__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__APPLIED_REQUIREMENTS = BOOLEAN_OPERATION__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION__CHILDREN = BOOLEAN_OPERATION__CHILDREN;

  /**
   * The number of structural features of the '<em>Or Operation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int OR_OPERATION_FEATURE_COUNT = BOOLEAN_OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.NotOperationImpl <em>Not Operation</em>}' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.NotOperationImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getNotOperation()
   * @generated
   */
  int NOT_OPERATION = 9;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__OWNED_EXTENSIONS = BOOLEAN_OPERATION__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__ID = BOOLEAN_OPERATION__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__SID = BOOLEAN_OPERATION__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__CONSTRAINTS = BOOLEAN_OPERATION__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__OWNED_CONSTRAINTS = BOOLEAN_OPERATION__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__INCOMING_TRACES = BOOLEAN_OPERATION__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__OUTGOING_TRACES = BOOLEAN_OPERATION__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__VISIBLE_IN_DOC = BOOLEAN_OPERATION__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__VISIBLE_IN_LM = BOOLEAN_OPERATION__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__SUMMARY = BOOLEAN_OPERATION__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__DESCRIPTION = BOOLEAN_OPERATION__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__REVIEW = BOOLEAN_OPERATION__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__OWNED_PROPERTY_VALUES = BOOLEAN_OPERATION__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = BOOLEAN_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__APPLIED_PROPERTY_VALUES = BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = BOOLEAN_OPERATION__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = BOOLEAN_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__STATUS = BOOLEAN_OPERATION__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__FEATURES = BOOLEAN_OPERATION__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__APPLIED_REQUIREMENTS = BOOLEAN_OPERATION__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION__CHILDREN = BOOLEAN_OPERATION__CHILDREN;

  /**
   * The number of structural features of the '<em>Not Operation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int NOT_OPERATION_FEATURE_COUNT = BOOLEAN_OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.ComparisonImpl <em>Comparison</em>}' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.ComparisonImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getComparison()
   * @generated
   */
  int COMPARISON = 10;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__ID = CapellacorePackage.NAMED_ELEMENT__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__SID = CapellacorePackage.NAMED_ELEMENT__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Configuration1</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__CONFIGURATION1 = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Situation</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__SITUATION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Configuration2</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON__CONFIGURATION2 = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Comparison</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPARISON_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.impl.ResultImpl <em>Result</em>}' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.impl.ResultImpl
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getResult()
   * @generated
   */
  int RESULT = 11;

  /**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

  /**
   * The feature id for the '<em><b>Sid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

  /**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

  /**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

  /**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Review</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

  /**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

  /**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

  /**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

  /**
   * The feature id for the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

  /**
   * The feature id for the '<em><b>Applied Requirements</b></em>' reference list. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

  /**
   * The feature id for the '<em><b>Situation</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT__SITUATION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Result</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESULT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.kind_Type <em>kind Type</em>}' enum. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.kind_Type
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getkind_Type()
   * @generated
   */
  int KIND_TYPE = 12;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.access_Type <em>access Type</em>}' enum. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.access_Type
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getaccess_Type()
   * @generated
   */
  int ACCESS_TYPE = 13;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.selector_Type <em>selector Type</em>}' enum. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.selector_Type
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getselector_Type()
   * @generated
   */
  int SELECTOR_TYPE = 14;

  /**
   * The meta object id for the '{@link org.polarsys.capella.vp.ms.ms_Type <em>ms Type</em>}' enum. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see org.polarsys.capella.vp.ms.ms_Type
   * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getms_Type()
   * @generated
   */
  int MS_TYPE = 15;

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.CSConfiguration <em>CS Configuration</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>CS Configuration</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration
   * @generated
   */
  EClass getCSConfiguration();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getSupportedModes
   * <em>Supported Modes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Supported Modes</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getSupportedModes()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_SupportedModes();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getElements
   * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Elements</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getElements()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_Elements();

  /**
   * Returns the meta object for the reference list
   * '{@link org.polarsys.capella.vp.ms.CSConfiguration#getDeploymentLinks <em>Deployment Links</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Deployment Links</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getDeploymentLinks()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_DeploymentLinks();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getFunctions
   * <em>Functions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Functions</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getFunctions()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_Functions();

  /**
   * Returns the meta object for the reference list
   * '{@link org.polarsys.capella.vp.ms.CSConfiguration#getFunctionalChains <em>Functional Chains</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Functional Chains</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getFunctionalChains()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_FunctionalChains();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getComponents
   * <em>Components</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Components</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getComponents()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_Components();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getPorts
   * <em>Ports</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Ports</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getPorts()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_Ports();

  /**
   * Returns the meta object for the reference list
   * '{@link org.polarsys.capella.vp.ms.CSConfiguration#getChildConfigurations <em>Child Configurations</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Child Configurations</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getChildConfigurations()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_ChildConfigurations();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.vp.ms.CSConfiguration#getKind
   * <em>Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getKind()
   * @see #getCSConfiguration()
   * @generated
   */
  EAttribute getCSConfiguration_Kind();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.vp.ms.CSConfiguration#getAccess
   * <em>Access</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Access</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getAccess()
   * @see #getCSConfiguration()
   * @generated
   */
  EAttribute getCSConfiguration_Access();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.vp.ms.CSConfiguration#getSelector
   * <em>Selector</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Selector</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getSelector()
   * @see #getCSConfiguration()
   * @generated
   */
  EAttribute getCSConfiguration_Selector();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getContext
   * <em>Context</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Context</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getContext()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_Context();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.CSConfiguration#getCompareTo
   * <em>Compare To</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Compare To</em>'.
   * @see org.polarsys.capella.vp.ms.CSConfiguration#getCompareTo()
   * @see #getCSConfiguration()
   * @generated
   */
  EReference getCSConfiguration_CompareTo();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.FSMType <em>FSM Type</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>FSM Type</em>'.
   * @see org.polarsys.capella.vp.ms.FSMType
   * @generated
   */
  EClass getFSMType();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.vp.ms.FSMType#getMs <em>Ms</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Ms</em>'.
   * @see org.polarsys.capella.vp.ms.FSMType#getMs()
   * @see #getFSMType()
   * @generated
   */
  EAttribute getFSMType_Ms();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.Situation <em>Situation</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Situation</em>'.
   * @see org.polarsys.capella.vp.ms.Situation
   * @generated
   */
  EClass getSituation();

  /**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.vp.ms.Situation#getExpression
   * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.polarsys.capella.vp.ms.Situation#getExpression()
   * @see #getSituation()
   * @generated
   */
  EReference getSituation_Expression();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.BooleanExpression <em>Boolean
   * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Boolean Expression</em>'.
   * @see org.polarsys.capella.vp.ms.BooleanExpression
   * @generated
   */
  EClass getBooleanExpression();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.BooleanOperation <em>Boolean Operation</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Boolean Operation</em>'.
   * @see org.polarsys.capella.vp.ms.BooleanOperation
   * @generated
   */
  EClass getBooleanOperation();

  /**
   * Returns the meta object for the containment reference list
   * '{@link org.polarsys.capella.vp.ms.BooleanOperation#getChildren <em>Children</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Children</em>'.
   * @see org.polarsys.capella.vp.ms.BooleanOperation#getChildren()
   * @see #getBooleanOperation()
   * @generated
   */
  EReference getBooleanOperation_Children();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.InStateExpression <em>In State
   * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>In State Expression</em>'.
   * @see org.polarsys.capella.vp.ms.InStateExpression
   * @generated
   */
  EClass getInStateExpression();

  /**
   * Returns the meta object for the reference '{@link org.polarsys.capella.vp.ms.InStateExpression#getState
   * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>State</em>'.
   * @see org.polarsys.capella.vp.ms.InStateExpression#getState()
   * @see #getInStateExpression()
   * @generated
   */
  EReference getInStateExpression_State();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.InSituationExpression <em>In Situation
   * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>In Situation Expression</em>'.
   * @see org.polarsys.capella.vp.ms.InSituationExpression
   * @generated
   */
  EClass getInSituationExpression();

  /**
   * Returns the meta object for the reference '{@link org.polarsys.capella.vp.ms.InSituationExpression#getSituation
   * <em>Situation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>Situation</em>'.
   * @see org.polarsys.capella.vp.ms.InSituationExpression#getSituation()
   * @see #getInSituationExpression()
   * @generated
   */
  EReference getInSituationExpression_Situation();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.AndOperation <em>And Operation</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>And Operation</em>'.
   * @see org.polarsys.capella.vp.ms.AndOperation
   * @generated
   */
  EClass getAndOperation();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.OrOperation <em>Or Operation</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Or Operation</em>'.
   * @see org.polarsys.capella.vp.ms.OrOperation
   * @generated
   */
  EClass getOrOperation();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.NotOperation <em>Not Operation</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Not Operation</em>'.
   * @see org.polarsys.capella.vp.ms.NotOperation
   * @generated
   */
  EClass getNotOperation();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.Comparison <em>Comparison</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Comparison</em>'.
   * @see org.polarsys.capella.vp.ms.Comparison
   * @generated
   */
  EClass getComparison();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.Comparison#getConfiguration1
   * <em>Configuration1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Configuration1</em>'.
   * @see org.polarsys.capella.vp.ms.Comparison#getConfiguration1()
   * @see #getComparison()
   * @generated
   */
  EReference getComparison_Configuration1();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.Comparison#getSituation
   * <em>Situation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Situation</em>'.
   * @see org.polarsys.capella.vp.ms.Comparison#getSituation()
   * @see #getComparison()
   * @generated
   */
  EReference getComparison_Situation();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.Comparison#getConfiguration2
   * <em>Configuration2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Configuration2</em>'.
   * @see org.polarsys.capella.vp.ms.Comparison#getConfiguration2()
   * @see #getComparison()
   * @generated
   */
  EReference getComparison_Configuration2();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.vp.ms.Result <em>Result</em>}'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Result</em>'.
   * @see org.polarsys.capella.vp.ms.Result
   * @generated
   */
  EClass getResult();

  /**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.vp.ms.Result#getSituation
   * <em>Situation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Situation</em>'.
   * @see org.polarsys.capella.vp.ms.Result#getSituation()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Situation();

  /**
   * Returns the meta object for enum '{@link org.polarsys.capella.vp.ms.kind_Type <em>kind Type</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for enum '<em>kind Type</em>'.
   * @see org.polarsys.capella.vp.ms.kind_Type
   * @generated
   */
  EEnum getkind_Type();

  /**
   * Returns the meta object for enum '{@link org.polarsys.capella.vp.ms.access_Type <em>access Type</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for enum '<em>access Type</em>'.
   * @see org.polarsys.capella.vp.ms.access_Type
   * @generated
   */
  EEnum getaccess_Type();

  /**
   * Returns the meta object for enum '{@link org.polarsys.capella.vp.ms.selector_Type <em>selector Type</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for enum '<em>selector Type</em>'.
   * @see org.polarsys.capella.vp.ms.selector_Type
   * @generated
   */
  EEnum getselector_Type();

  /**
   * Returns the meta object for enum '{@link org.polarsys.capella.vp.ms.ms_Type <em>ms Type</em>}'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return the meta object for enum '<em>ms Type</em>'.
   * @see org.polarsys.capella.vp.ms.ms_Type
   * @generated
   */
  EEnum getms_Type();

  /**
   * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MsFactory getMsFactory();

  /**
   * <!-- begin-user-doc --> Defines literals for the meta objects that represent
   * <ul>
   * <li>each class,</li>
   * <li>each feature of each class,</li>
   * <li>each operation of each class,</li>
   * <li>each enum,</li>
   * <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.CSConfigurationImpl <em>CS
     * Configuration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.CSConfigurationImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getCSConfiguration()
     * @generated
     */
    EClass CS_CONFIGURATION = eINSTANCE.getCSConfiguration();

    /**
     * The meta object literal for the '<em><b>Supported Modes</b></em>' reference list feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__SUPPORTED_MODES = eINSTANCE.getCSConfiguration_SupportedModes();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__ELEMENTS = eINSTANCE.getCSConfiguration_Elements();

    /**
     * The meta object literal for the '<em><b>Deployment Links</b></em>' reference list feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__DEPLOYMENT_LINKS = eINSTANCE.getCSConfiguration_DeploymentLinks();

    /**
     * The meta object literal for the '<em><b>Functions</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__FUNCTIONS = eINSTANCE.getCSConfiguration_Functions();

    /**
     * The meta object literal for the '<em><b>Functional Chains</b></em>' reference list feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__FUNCTIONAL_CHAINS = eINSTANCE.getCSConfiguration_FunctionalChains();

    /**
     * The meta object literal for the '<em><b>Components</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__COMPONENTS = eINSTANCE.getCSConfiguration_Components();

    /**
     * The meta object literal for the '<em><b>Ports</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__PORTS = eINSTANCE.getCSConfiguration_Ports();

    /**
     * The meta object literal for the '<em><b>Child Configurations</b></em>' reference list feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__CHILD_CONFIGURATIONS = eINSTANCE.getCSConfiguration_ChildConfigurations();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EAttribute CS_CONFIGURATION__KIND = eINSTANCE.getCSConfiguration_Kind();

    /**
     * The meta object literal for the '<em><b>Access</b></em>' attribute feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EAttribute CS_CONFIGURATION__ACCESS = eINSTANCE.getCSConfiguration_Access();

    /**
     * The meta object literal for the '<em><b>Selector</b></em>' attribute feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EAttribute CS_CONFIGURATION__SELECTOR = eINSTANCE.getCSConfiguration_Selector();

    /**
     * The meta object literal for the '<em><b>Context</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__CONTEXT = eINSTANCE.getCSConfiguration_Context();

    /**
     * The meta object literal for the '<em><b>Compare To</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference CS_CONFIGURATION__COMPARE_TO = eINSTANCE.getCSConfiguration_CompareTo();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.FSMTypeImpl <em>FSM Type</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.FSMTypeImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getFSMType()
     * @generated
     */
    EClass FSM_TYPE = eINSTANCE.getFSMType();

    /**
     * The meta object literal for the '<em><b>Ms</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    EAttribute FSM_TYPE__MS = eINSTANCE.getFSMType_Ms();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.SituationImpl <em>Situation</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.SituationImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getSituation()
     * @generated
     */
    EClass SITUATION = eINSTANCE.getSituation();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference SITUATION__EXPRESSION = eINSTANCE.getSituation_Expression();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.BooleanExpressionImpl <em>Boolean
     * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.BooleanExpressionImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getBooleanExpression()
     * @generated
     */
    EClass BOOLEAN_EXPRESSION = eINSTANCE.getBooleanExpression();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.BooleanOperationImpl <em>Boolean
     * Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.BooleanOperationImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getBooleanOperation()
     * @generated
     */
    EClass BOOLEAN_OPERATION = eINSTANCE.getBooleanOperation();

    /**
     * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference BOOLEAN_OPERATION__CHILDREN = eINSTANCE.getBooleanOperation_Children();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.InStateExpressionImpl <em>In State
     * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.InStateExpressionImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getInStateExpression()
     * @generated
     */
    EClass IN_STATE_EXPRESSION = eINSTANCE.getInStateExpression();

    /**
     * The meta object literal for the '<em><b>State</b></em>' reference feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference IN_STATE_EXPRESSION__STATE = eINSTANCE.getInStateExpression_State();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.InSituationExpressionImpl <em>In
     * Situation Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.InSituationExpressionImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getInSituationExpression()
     * @generated
     */
    EClass IN_SITUATION_EXPRESSION = eINSTANCE.getInSituationExpression();

    /**
     * The meta object literal for the '<em><b>Situation</b></em>' reference feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference IN_SITUATION_EXPRESSION__SITUATION = eINSTANCE.getInSituationExpression_Situation();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.AndOperationImpl <em>And Operation</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.AndOperationImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getAndOperation()
     * @generated
     */
    EClass AND_OPERATION = eINSTANCE.getAndOperation();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.OrOperationImpl <em>Or Operation</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.OrOperationImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getOrOperation()
     * @generated
     */
    EClass OR_OPERATION = eINSTANCE.getOrOperation();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.NotOperationImpl <em>Not Operation</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.NotOperationImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getNotOperation()
     * @generated
     */
    EClass NOT_OPERATION = eINSTANCE.getNotOperation();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.ComparisonImpl <em>Comparison</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.ComparisonImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getComparison()
     * @generated
     */
    EClass COMPARISON = eINSTANCE.getComparison();

    /**
     * The meta object literal for the '<em><b>Configuration1</b></em>' reference list feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference COMPARISON__CONFIGURATION1 = eINSTANCE.getComparison_Configuration1();

    /**
     * The meta object literal for the '<em><b>Situation</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference COMPARISON__SITUATION = eINSTANCE.getComparison_Situation();

    /**
     * The meta object literal for the '<em><b>Configuration2</b></em>' reference list feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference COMPARISON__CONFIGURATION2 = eINSTANCE.getComparison_Configuration2();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.impl.ResultImpl <em>Result</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.impl.ResultImpl
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getResult()
     * @generated
     */
    EClass RESULT = eINSTANCE.getResult();

    /**
     * The meta object literal for the '<em><b>Situation</b></em>' reference list feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    EReference RESULT__SITUATION = eINSTANCE.getResult_Situation();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.kind_Type <em>kind Type</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.kind_Type
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getkind_Type()
     * @generated
     */
    EEnum KIND_TYPE = eINSTANCE.getkind_Type();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.access_Type <em>access Type</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.access_Type
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getaccess_Type()
     * @generated
     */
    EEnum ACCESS_TYPE = eINSTANCE.getaccess_Type();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.selector_Type <em>selector Type</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.selector_Type
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getselector_Type()
     * @generated
     */
    EEnum SELECTOR_TYPE = eINSTANCE.getselector_Type();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.vp.ms.ms_Type <em>ms Type</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.polarsys.capella.vp.ms.ms_Type
     * @see org.polarsys.capella.vp.ms.impl.MsPackageImpl#getms_Type()
     * @generated
     */
    EEnum MS_TYPE = eINSTANCE.getms_Type();

  }

} // MsPackage
