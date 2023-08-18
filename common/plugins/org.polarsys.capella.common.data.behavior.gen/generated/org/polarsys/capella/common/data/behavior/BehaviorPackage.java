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
package org.polarsys.capella.common.data.behavior;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

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
 * @see org.polarsys.capella.common.data.behavior.BehaviorFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Behaviour aims at defining the core concepts of behavioural model.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model ModellingCore.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface BehaviorPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "behavior"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/common/behavior/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.common.data.behavior"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	BehaviorPackage eINSTANCE = org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior <em>Abstract Behavior</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractBehavior()
   * @generated
   */
	int ABSTRACT_BEHAVIOR = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__ID = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__SID = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__CONSTRAINTS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__NAME = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Is Control Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Parameter Set</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR__OWNED_PARAMETER = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Abstract Behavior</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BEHAVIOR_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.AbstractSignal <em>Abstract Signal</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.AbstractSignal
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractSignal()
   * @generated
   */
	int ABSTRACT_SIGNAL = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__ID = ModellingcorePackage.ABSTRACT_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__SID = ModellingcorePackage.ABSTRACT_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__NAME = ModellingcorePackage.ABSTRACT_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL__ABSTRACT_TYPED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Abstract Signal</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.AbstractEvent <em>Abstract Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.AbstractEvent
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractEvent()
   * @generated
   */
	int ABSTRACT_EVENT = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__ID = ModellingcorePackage.ABSTRACT_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__SID = ModellingcorePackage.ABSTRACT_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__NAME = ModellingcorePackage.ABSTRACT_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT__ABSTRACT_TYPED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Abstract Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent <em>Abstract Time Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.AbstractTimeEvent
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractTimeEvent()
   * @generated
   */
	int ABSTRACT_TIME_EVENT = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__OWNED_EXTENSIONS = ABSTRACT_EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__ID = ABSTRACT_EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__SID = ABSTRACT_EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__CONSTRAINTS = ABSTRACT_EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__OWNED_CONSTRAINTS = ABSTRACT_EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__OWNED_MIGRATED_ELEMENTS = ABSTRACT_EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__NAME = ABSTRACT_EVENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Is Relative</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__IS_RELATIVE = ABSTRACT_EVENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>When</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT__WHEN = ABSTRACT_EVENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Time Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TIME_EVENT_FEATURE_COUNT = ABSTRACT_EVENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.AbstractMessageEvent <em>Abstract Message Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.AbstractMessageEvent
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractMessageEvent()
   * @generated
   */
	int ABSTRACT_MESSAGE_EVENT = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__OWNED_EXTENSIONS = ABSTRACT_EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__ID = ABSTRACT_EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__SID = ABSTRACT_EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__CONSTRAINTS = ABSTRACT_EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__OWNED_CONSTRAINTS = ABSTRACT_EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__OWNED_MIGRATED_ELEMENTS = ABSTRACT_EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__NAME = ABSTRACT_EVENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Abstract Message Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MESSAGE_EVENT_FEATURE_COUNT = ABSTRACT_EVENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent <em>Abstract Signal Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.AbstractSignalEvent
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractSignalEvent()
   * @generated
   */
	int ABSTRACT_SIGNAL_EVENT = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__OWNED_EXTENSIONS = ABSTRACT_MESSAGE_EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__ID = ABSTRACT_MESSAGE_EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__SID = ABSTRACT_MESSAGE_EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__CONSTRAINTS = ABSTRACT_MESSAGE_EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__OWNED_CONSTRAINTS = ABSTRACT_MESSAGE_EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__OWNED_MIGRATED_ELEMENTS = ABSTRACT_MESSAGE_EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__NAME = ABSTRACT_MESSAGE_EVENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_MESSAGE_EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Signal</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT__SIGNAL = ABSTRACT_MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Signal Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_SIGNAL_EVENT_FEATURE_COUNT = ABSTRACT_MESSAGE_EVENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.behavior.TimeExpression <em>Time Expression</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.behavior.TimeExpression
   * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getTimeExpression()
   * @generated
   */
	int TIME_EXPRESSION = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__OWNED_EXTENSIONS = ModellingcorePackage.VALUE_SPECIFICATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__ID = ModellingcorePackage.VALUE_SPECIFICATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__SID = ModellingcorePackage.VALUE_SPECIFICATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__CONSTRAINTS = ModellingcorePackage.VALUE_SPECIFICATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__OWNED_CONSTRAINTS = ModellingcorePackage.VALUE_SPECIFICATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.VALUE_SPECIFICATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__NAME = ModellingcorePackage.VALUE_SPECIFICATION__NAME;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__ABSTRACT_TYPE = ModellingcorePackage.VALUE_SPECIFICATION__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Observations</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__OBSERVATIONS = ModellingcorePackage.VALUE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Expression</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION__EXPRESSION = ModellingcorePackage.VALUE_SPECIFICATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Time Expression</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_EXPRESSION_FEATURE_COUNT = ModellingcorePackage.VALUE_SPECIFICATION_FEATURE_COUNT + 2;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior <em>Abstract Behavior</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Behavior</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior
   * @generated
   */
	EClass getAbstractBehavior();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#isIsControlOperator <em>Is Control Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Control Operator</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior#isIsControlOperator()
   * @see #getAbstractBehavior()
   * @generated
   */
	EAttribute getAbstractBehavior_IsControlOperator();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#getOwnedParameterSet <em>Owned Parameter Set</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Owned Parameter Set</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior#getOwnedParameterSet()
   * @see #getAbstractBehavior()
   * @generated
   */
	EReference getAbstractBehavior_OwnedParameterSet();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior#getOwnedParameter <em>Owned Parameter</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Owned Parameter</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractBehavior#getOwnedParameter()
   * @see #getAbstractBehavior()
   * @generated
   */
	EReference getAbstractBehavior_OwnedParameter();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.AbstractSignal <em>Abstract Signal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Signal</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractSignal
   * @generated
   */
	EClass getAbstractSignal();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.AbstractEvent <em>Abstract Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Event</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractEvent
   * @generated
   */
	EClass getAbstractEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent <em>Abstract Time Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Time Event</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractTimeEvent
   * @generated
   */
	EClass getAbstractTimeEvent();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent#isIsRelative <em>Is Relative</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Relative</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractTimeEvent#isIsRelative()
   * @see #getAbstractTimeEvent()
   * @generated
   */
	EAttribute getAbstractTimeEvent_IsRelative();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent#getWhen <em>When</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>When</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractTimeEvent#getWhen()
   * @see #getAbstractTimeEvent()
   * @generated
   */
	EReference getAbstractTimeEvent_When();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.AbstractMessageEvent <em>Abstract Message Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Message Event</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractMessageEvent
   * @generated
   */
	EClass getAbstractMessageEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent <em>Abstract Signal Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Signal Event</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractSignalEvent
   * @generated
   */
	EClass getAbstractSignalEvent();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent#getSignal <em>Signal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Signal</em>'.
   * @see org.polarsys.capella.common.data.behavior.AbstractSignalEvent#getSignal()
   * @see #getAbstractSignalEvent()
   * @generated
   */
	EReference getAbstractSignalEvent_Signal();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.behavior.TimeExpression <em>Time Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Expression</em>'.
   * @see org.polarsys.capella.common.data.behavior.TimeExpression
   * @generated
   */
	EClass getTimeExpression();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.behavior.TimeExpression#getObservations <em>Observations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Observations</em>'.
   * @see org.polarsys.capella.common.data.behavior.TimeExpression#getObservations()
   * @see #getTimeExpression()
   * @generated
   */
	EReference getTimeExpression_Observations();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.behavior.TimeExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Expression</em>'.
   * @see org.polarsys.capella.common.data.behavior.TimeExpression#getExpression()
   * @see #getTimeExpression()
   * @generated
   */
	EReference getTimeExpression_Expression();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	BehaviorFactory getBehaviorFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.AbstractBehavior <em>Abstract Behavior</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.AbstractBehavior
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractBehavior()
     * @generated
     */
		EClass ABSTRACT_BEHAVIOR = eINSTANCE.getAbstractBehavior();

		/**
     * The meta object literal for the '<em><b>Is Control Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR = eINSTANCE.getAbstractBehavior_IsControlOperator();

		/**
     * The meta object literal for the '<em><b>Owned Parameter Set</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET = eINSTANCE.getAbstractBehavior_OwnedParameterSet();

		/**
     * The meta object literal for the '<em><b>Owned Parameter</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_BEHAVIOR__OWNED_PARAMETER = eINSTANCE.getAbstractBehavior_OwnedParameter();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.AbstractSignal <em>Abstract Signal</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.AbstractSignal
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractSignal()
     * @generated
     */
		EClass ABSTRACT_SIGNAL = eINSTANCE.getAbstractSignal();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.AbstractEvent <em>Abstract Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.AbstractEvent
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractEvent()
     * @generated
     */
		EClass ABSTRACT_EVENT = eINSTANCE.getAbstractEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.AbstractTimeEvent <em>Abstract Time Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.AbstractTimeEvent
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractTimeEvent()
     * @generated
     */
		EClass ABSTRACT_TIME_EVENT = eINSTANCE.getAbstractTimeEvent();

		/**
     * The meta object literal for the '<em><b>Is Relative</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_TIME_EVENT__IS_RELATIVE = eINSTANCE.getAbstractTimeEvent_IsRelative();

		/**
     * The meta object literal for the '<em><b>When</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_TIME_EVENT__WHEN = eINSTANCE.getAbstractTimeEvent_When();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.AbstractMessageEvent <em>Abstract Message Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.AbstractMessageEvent
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractMessageEvent()
     * @generated
     */
		EClass ABSTRACT_MESSAGE_EVENT = eINSTANCE.getAbstractMessageEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.AbstractSignalEvent <em>Abstract Signal Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.AbstractSignalEvent
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getAbstractSignalEvent()
     * @generated
     */
		EClass ABSTRACT_SIGNAL_EVENT = eINSTANCE.getAbstractSignalEvent();

		/**
     * The meta object literal for the '<em><b>Signal</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_SIGNAL_EVENT__SIGNAL = eINSTANCE.getAbstractSignalEvent_Signal();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.behavior.TimeExpression <em>Time Expression</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.behavior.TimeExpression
     * @see org.polarsys.capella.common.data.behavior.impl.BehaviorPackageImpl#getTimeExpression()
     * @generated
     */
		EClass TIME_EXPRESSION = eINSTANCE.getTimeExpression();

		/**
     * The meta object literal for the '<em><b>Observations</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TIME_EXPRESSION__OBSERVATIONS = eINSTANCE.getTimeExpression_Observations();

		/**
     * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TIME_EXPRESSION__EXPRESSION = eINSTANCE.getTimeExpression_Expression();

	}

} //BehaviorPackage
