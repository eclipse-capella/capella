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
package org.polarsys.capella.common.data.modellingcore;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.kitalpha.emde.model.EmdePackage;

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
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcoreFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping profileName='ModellingCore'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='ModellingCore aims at defining the core concepts of any modelling language.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ModellingcorePackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "modellingcore"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/common/core/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.common.data.core"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	ModellingcorePackage eINSTANCE = org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl <em>Model Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getModelElement()
   * @generated
   */
	int MODEL_ELEMENT = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT__OWNED_EXTENSIONS = EmdePackage.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT__ID = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT__SID = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT__CONSTRAINTS = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT__OWNED_CONSTRAINTS = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Model Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODEL_ELEMENT_FEATURE_COUNT = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractRelationshipImpl <em>Abstract Relationship</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractRelationshipImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractRelationship()
   * @generated
   */
	int ABSTRACT_RELATIONSHIP = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP__REALIZED_FLOW = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Relationship</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_RELATIONSHIP_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractNamedElementImpl <em>Abstract Named Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractNamedElementImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractNamedElement()
   * @generated
   */
	int ABSTRACT_NAMED_ELEMENT = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT__NAME = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Named Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl <em>Informations Exchanger</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getInformationsExchanger()
   * @generated
   */
	int INFORMATIONS_EXCHANGER = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER__INFORMATION_FLOWS = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Informations Exchanger</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATIONS_EXCHANGER_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getTraceableElement()
   * @generated
   */
	int TRACEABLE_ELEMENT = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__INCOMING_TRACES = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT__OUTGOING_TRACES = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Traceable Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACEABLE_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.FinalizableElementImpl <em>Finalizable Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.FinalizableElementImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getFinalizableElement()
   * @generated
   */
	int FINALIZABLE_ELEMENT = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT__FINAL = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Finalizable Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FINALIZABLE_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.PublishableElementImpl <em>Publishable Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.PublishableElementImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getPublishableElement()
   * @generated
   */
	int PUBLISHABLE_ELEMENT = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT__VISIBLE_IN_LM = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Publishable Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PUBLISHABLE_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractTypeImpl <em>Abstract Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractTypeImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractType()
   * @generated
   */
	int ABSTRACT_TYPE = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__OWNED_EXTENSIONS = ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__ID = ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__SID = ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__OWNED_CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__NAME = ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPE_FEATURE_COUNT = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractTypedElementImpl <em>Abstract Typed Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractTypedElementImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractTypedElement()
   * @generated
   */
	int ABSTRACT_TYPED_ELEMENT = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__OWNED_EXTENSIONS = ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__ID = ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__SID = ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__OWNED_CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__OWNED_MIGRATED_ELEMENTS = ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__NAME = ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Typed Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace <em>Abstract Trace</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractTrace()
   * @generated
   */
	int ABSTRACT_TRACE = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__OWNED_EXTENSIONS = TRACEABLE_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__ID = TRACEABLE_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__SID = TRACEABLE_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__CONSTRAINTS = TRACEABLE_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__OWNED_CONSTRAINTS = TRACEABLE_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__OWNED_MIGRATED_ELEMENTS = TRACEABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__INCOMING_TRACES = TRACEABLE_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__OUTGOING_TRACES = TRACEABLE_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__TARGET_ELEMENT = TRACEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE__SOURCE_ELEMENT = TRACEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Trace</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_TRACE_FEATURE_COUNT = TRACEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint <em>Abstract Constraint</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.AbstractConstraint
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractConstraint()
   * @generated
   */
	int ABSTRACT_CONSTRAINT = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__OWNED_EXTENSIONS = MODEL_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__ID = MODEL_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__SID = MODEL_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__CONSTRAINTS = MODEL_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__OWNED_CONSTRAINTS = MODEL_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__OWNED_MIGRATED_ELEMENTS = MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Constrained Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Specification</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT__CONTEXT = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Abstract Constraint</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONSTRAINT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.ValueSpecification <em>Value Specification</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.ValueSpecification
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getValueSpecification()
   * @generated
   */
	int VALUE_SPECIFICATION = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__OWNED_EXTENSIONS = ABSTRACT_TYPED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__ID = ABSTRACT_TYPED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__SID = ABSTRACT_TYPED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__CONSTRAINTS = ABSTRACT_TYPED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__OWNED_CONSTRAINTS = ABSTRACT_TYPED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_TYPED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__NAME = ABSTRACT_TYPED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION__ABSTRACT_TYPE = ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;

	/**
   * The number of structural features of the '<em>Value Specification</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_SPECIFICATION_FEATURE_COUNT = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl <em>Abstract Parameter</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractParameter()
   * @generated
   */
	int ABSTRACT_PARAMETER = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__OWNED_EXTENSIONS = ABSTRACT_TYPED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__ID = ABSTRACT_TYPED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__SID = ABSTRACT_TYPED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__CONSTRAINTS = ABSTRACT_TYPED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__OWNED_CONSTRAINTS = ABSTRACT_TYPED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__OWNED_MIGRATED_ELEMENTS = ABSTRACT_TYPED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__NAME = ABSTRACT_TYPED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__ABSTRACT_TYPE = ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Is Exception</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__IS_EXCEPTION = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Stream</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__IS_STREAM = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Is Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__IS_OPTIONAL = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Kind Of Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__KIND_OF_RATE = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Effect</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__EFFECT = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Rate</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__RATE = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Probability</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__PROBABILITY = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Parameter Set</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER__PARAMETER_SET = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The number of structural features of the '<em>Abstract Parameter</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_FEATURE_COUNT = ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl <em>Abstract Parameter Set</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractParameterSet()
   * @generated
   */
	int ABSTRACT_PARAMETER_SET = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__OWNED_EXTENSIONS = ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__ID = ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__SID = ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__OWNED_CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__OWNED_MIGRATED_ELEMENTS = ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__NAME = ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Owned Conditions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Probability</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__PROBABILITY = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Parameters</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET__PARAMETERS = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Abstract Parameter Set</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PARAMETER_SET_FEATURE_COUNT = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl <em>Abstract Information Flow</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractInformationFlow()
   * @generated
   */
	int ABSTRACT_INFORMATION_FLOW = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__OWNED_EXTENSIONS = ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__ID = ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__SID = ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__OWNED_CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__OWNED_MIGRATED_ELEMENTS = ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__NAME = ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__REALIZATIONS = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Convoyed Informations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__SOURCE = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW__TARGET = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Abstract Information Flow</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INFORMATION_FLOW_FEATURE_COUNT = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractExchangeItemImpl <em>Abstract Exchange Item</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractExchangeItemImpl
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractExchangeItem()
   * @generated
   */
	int ABSTRACT_EXCHANGE_ITEM = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__OWNED_EXTENSIONS = ABSTRACT_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__ID = ABSTRACT_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__SID = ABSTRACT_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__CONSTRAINTS = ABSTRACT_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__OWNED_CONSTRAINTS = ABSTRACT_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__OWNED_MIGRATED_ELEMENTS = ABSTRACT_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__NAME = ABSTRACT_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Abstract Exchange Item</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT = ABSTRACT_TYPE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.IState <em>IState</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.IState
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getIState()
   * @generated
   */
	int ISTATE = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__OWNED_EXTENSIONS = ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__ID = ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__SID = ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__OWNED_CONSTRAINTS = ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__NAME = ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Referenced States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE__REFERENCED_STATES = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Exploited States</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISTATE__EXPLOITED_STATES = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>IState</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ISTATE_FEATURE_COUNT = ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.ParameterEffectKind <em>Parameter Effect Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.ParameterEffectKind
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getParameterEffectKind()
   * @generated
   */
	int PARAMETER_EFFECT_KIND = 17;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.data.modellingcore.RateKind <em>Rate Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.data.modellingcore.RateKind
   * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getRateKind()
   * @generated
   */
	int RATE_KIND = 18;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.ModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement
   * @generated
   */
	EClass getModelElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.ModelElement#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement#getId()
   * @see #getModelElement()
   * @generated
   */
	EAttribute getModelElement_Id();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.ModelElement#getSid <em>Sid</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sid</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement#getSid()
   * @see #getModelElement()
   * @generated
   */
	EAttribute getModelElement_Sid();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.ModelElement#getConstraints <em>Constraints</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Constraints</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement#getConstraints()
   * @see #getModelElement()
   * @generated
   */
	EReference getModelElement_Constraints();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.data.modellingcore.ModelElement#getOwnedConstraints <em>Owned Constraints</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Constraints</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement#getOwnedConstraints()
   * @see #getModelElement()
   * @generated
   */
	EReference getModelElement_OwnedConstraints();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.data.modellingcore.ModelElement#getOwnedMigratedElements <em>Owned Migrated Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Migrated Elements</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement#getOwnedMigratedElements()
   * @see #getModelElement()
   * @generated
   */
	EReference getModelElement_OwnedMigratedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractRelationship <em>Abstract Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Relationship</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractRelationship
   * @generated
   */
	EClass getAbstractRelationship();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractRelationship#getRealizedFlow <em>Realized Flow</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Flow</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractRelationship#getRealizedFlow()
   * @see #getAbstractRelationship()
   * @generated
   */
	EReference getAbstractRelationship_RealizedFlow();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement <em>Abstract Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Named Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractNamedElement
   * @generated
   */
	EClass getAbstractNamedElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractNamedElement#getName()
   * @see #getAbstractNamedElement()
   * @generated
   */
	EAttribute getAbstractNamedElement_Name();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger <em>Informations Exchanger</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Informations Exchanger</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.InformationsExchanger
   * @generated
   */
	EClass getInformationsExchanger();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getIncomingInformationFlows <em>Incoming Information Flows</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Information Flows</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getIncomingInformationFlows()
   * @see #getInformationsExchanger()
   * @generated
   */
	EReference getInformationsExchanger_IncomingInformationFlows();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Information Flows</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getOutgoingInformationFlows()
   * @see #getInformationsExchanger()
   * @generated
   */
	EReference getInformationsExchanger_OutgoingInformationFlows();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getInformationFlows <em>Information Flows</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Information Flows</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getInformationFlows()
   * @see #getInformationsExchanger()
   * @generated
   */
	EReference getInformationsExchanger_InformationFlows();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Traceable Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
   * @generated
   */
	EClass getTraceableElement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement#getIncomingTraces <em>Incoming Traces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Traces</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement#getIncomingTraces()
   * @see #getTraceableElement()
   * @generated
   */
	EReference getTraceableElement_IncomingTraces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement#getOutgoingTraces <em>Outgoing Traces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Traces</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement#getOutgoingTraces()
   * @see #getTraceableElement()
   * @generated
   */
	EReference getTraceableElement_OutgoingTraces();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.FinalizableElement <em>Finalizable Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Finalizable Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.FinalizableElement
   * @generated
   */
	EClass getFinalizableElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.FinalizableElement#isFinal <em>Final</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Final</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.FinalizableElement#isFinal()
   * @see #getFinalizableElement()
   * @generated
   */
	EAttribute getFinalizableElement_Final();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement <em>Publishable Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Publishable Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement
   * @generated
   */
	EClass getPublishableElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInDoc <em>Visible In Doc</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visible In Doc</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInDoc()
   * @see #getPublishableElement()
   * @generated
   */
	EAttribute getPublishableElement_VisibleInDoc();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInLM <em>Visible In LM</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visible In LM</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement#isVisibleInLM()
   * @see #getPublishableElement()
   * @generated
   */
	EAttribute getPublishableElement_VisibleInLM();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractType <em>Abstract Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Type</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractType
   * @generated
   */
	EClass getAbstractType();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractType#getAbstractTypedElements <em>Abstract Typed Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Abstract Typed Elements</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractType#getAbstractTypedElements()
   * @see #getAbstractType()
   * @generated
   */
	EReference getAbstractType_AbstractTypedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractTypedElement <em>Abstract Typed Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Typed Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTypedElement
   * @generated
   */
	EClass getAbstractTypedElement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractTypedElement#getAbstractType <em>Abstract Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Abstract Type</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTypedElement#getAbstractType()
   * @see #getAbstractTypedElement()
   * @generated
   */
	EReference getAbstractTypedElement_AbstractType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace <em>Abstract Trace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Trace</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace
   * @generated
   */
	EClass getAbstractTrace();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace#getTargetElement <em>Target Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace#getTargetElement()
   * @see #getAbstractTrace()
   * @generated
   */
	EReference getAbstractTrace_TargetElement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace#getSourceElement <em>Source Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Element</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace#getSourceElement()
   * @see #getAbstractTrace()
   * @generated
   */
	EReference getAbstractTrace_SourceElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint <em>Abstract Constraint</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Constraint</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractConstraint
   * @generated
   */
	EClass getAbstractConstraint();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getConstrainedElements <em>Constrained Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Constrained Elements</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getConstrainedElements()
   * @see #getAbstractConstraint()
   * @generated
   */
	EReference getAbstractConstraint_ConstrainedElements();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getOwnedSpecification <em>Owned Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Specification</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getOwnedSpecification()
   * @see #getAbstractConstraint()
   * @generated
   */
	EReference getAbstractConstraint_OwnedSpecification();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Context</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getContext()
   * @see #getAbstractConstraint()
   * @generated
   */
	EReference getAbstractConstraint_Context();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.ValueSpecification <em>Value Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Value Specification</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ValueSpecification
   * @generated
   */
	EClass getValueSpecification();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter <em>Abstract Parameter</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Parameter</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter
   * @generated
   */
	EClass getAbstractParameter();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsException <em>Is Exception</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Exception</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsException()
   * @see #getAbstractParameter()
   * @generated
   */
	EAttribute getAbstractParameter_IsException();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsStream <em>Is Stream</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Stream</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsStream()
   * @see #getAbstractParameter()
   * @generated
   */
	EAttribute getAbstractParameter_IsStream();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsOptional <em>Is Optional</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Optional</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsOptional()
   * @see #getAbstractParameter()
   * @generated
   */
	EAttribute getAbstractParameter_IsOptional();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getKindOfRate <em>Kind Of Rate</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind Of Rate</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#getKindOfRate()
   * @see #getAbstractParameter()
   * @generated
   */
	EAttribute getAbstractParameter_KindOfRate();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getEffect <em>Effect</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Effect</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#getEffect()
   * @see #getAbstractParameter()
   * @generated
   */
	EAttribute getAbstractParameter_Effect();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getRate <em>Rate</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rate</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#getRate()
   * @see #getAbstractParameter()
   * @generated
   */
	EReference getAbstractParameter_Rate();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getProbability <em>Probability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Probability</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#getProbability()
   * @see #getAbstractParameter()
   * @generated
   */
	EReference getAbstractParameter_Probability();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getParameterSet <em>Parameter Set</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Parameter Set</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameter#getParameterSet()
   * @see #getAbstractParameter()
   * @generated
   */
	EReference getAbstractParameter_ParameterSet();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet <em>Abstract Parameter Set</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Parameter Set</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameterSet
   * @generated
   */
	EClass getAbstractParameterSet();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getOwnedConditions <em>Owned Conditions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Conditions</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getOwnedConditions()
   * @see #getAbstractParameterSet()
   * @generated
   */
	EReference getAbstractParameterSet_OwnedConditions();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getProbability <em>Probability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Probability</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getProbability()
   * @see #getAbstractParameterSet()
   * @generated
   */
	EReference getAbstractParameterSet_Probability();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Parameters</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getParameters()
   * @see #getAbstractParameterSet()
   * @generated
   */
	EReference getAbstractParameterSet_Parameters();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow <em>Abstract Information Flow</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Information Flow</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow
   * @generated
   */
	EClass getAbstractInformationFlow();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getRealizations <em>Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizations</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getRealizations()
   * @see #getAbstractInformationFlow()
   * @generated
   */
	EReference getAbstractInformationFlow_Realizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getConvoyedInformations <em>Convoyed Informations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Convoyed Informations</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getConvoyedInformations()
   * @see #getAbstractInformationFlow()
   * @generated
   */
	EReference getAbstractInformationFlow_ConvoyedInformations();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getSource()
   * @see #getAbstractInformationFlow()
   * @generated
   */
	EReference getAbstractInformationFlow_Source();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getTarget()
   * @see #getAbstractInformationFlow()
   * @generated
   */
	EReference getAbstractInformationFlow_Target();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem <em>Abstract Exchange Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Exchange Item</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem
   * @generated
   */
	EClass getAbstractExchangeItem();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.data.modellingcore.IState <em>IState</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>IState</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.IState
   * @generated
   */
	EClass getIState();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.IState#getReferencedStates <em>Referenced States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced States</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.IState#getReferencedStates()
   * @see #getIState()
   * @generated
   */
	EReference getIState_ReferencedStates();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.data.modellingcore.IState#getExploitedStates <em>Exploited States</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exploited States</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.IState#getExploitedStates()
   * @see #getIState()
   * @generated
   */
  EReference getIState_ExploitedStates();

  /**
   * Returns the meta object for enum '{@link org.polarsys.capella.common.data.modellingcore.ParameterEffectKind <em>Parameter Effect Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Parameter Effect Kind</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.ParameterEffectKind
   * @generated
   */
	EEnum getParameterEffectKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.common.data.modellingcore.RateKind <em>Rate Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Rate Kind</em>'.
   * @see org.polarsys.capella.common.data.modellingcore.RateKind
   * @generated
   */
	EEnum getRateKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	ModellingcoreFactory getModellingcoreFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl <em>Model Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getModelElement()
     * @generated
     */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MODEL_ELEMENT__ID = eINSTANCE.getModelElement_Id();

		/**
     * The meta object literal for the '<em><b>Sid</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MODEL_ELEMENT__SID = eINSTANCE.getModelElement_Sid();

		/**
     * The meta object literal for the '<em><b>Constraints</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MODEL_ELEMENT__CONSTRAINTS = eINSTANCE.getModelElement_Constraints();

		/**
     * The meta object literal for the '<em><b>Owned Constraints</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MODEL_ELEMENT__OWNED_CONSTRAINTS = eINSTANCE.getModelElement_OwnedConstraints();

		/**
     * The meta object literal for the '<em><b>Owned Migrated Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS = eINSTANCE.getModelElement_OwnedMigratedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractRelationshipImpl <em>Abstract Relationship</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractRelationshipImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractRelationship()
     * @generated
     */
		EClass ABSTRACT_RELATIONSHIP = eINSTANCE.getAbstractRelationship();

		/**
     * The meta object literal for the '<em><b>Realized Flow</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_RELATIONSHIP__REALIZED_FLOW = eINSTANCE.getAbstractRelationship_RealizedFlow();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractNamedElementImpl <em>Abstract Named Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractNamedElementImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractNamedElement()
     * @generated
     */
		EClass ABSTRACT_NAMED_ELEMENT = eINSTANCE.getAbstractNamedElement();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_NAMED_ELEMENT__NAME = eINSTANCE.getAbstractNamedElement_Name();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl <em>Informations Exchanger</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getInformationsExchanger()
     * @generated
     */
		EClass INFORMATIONS_EXCHANGER = eINSTANCE.getInformationsExchanger();

		/**
     * The meta object literal for the '<em><b>Incoming Information Flows</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS = eINSTANCE.getInformationsExchanger_IncomingInformationFlows();

		/**
     * The meta object literal for the '<em><b>Outgoing Information Flows</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS = eINSTANCE.getInformationsExchanger_OutgoingInformationFlows();

		/**
     * The meta object literal for the '<em><b>Information Flows</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INFORMATIONS_EXCHANGER__INFORMATION_FLOWS = eINSTANCE.getInformationsExchanger_InformationFlows();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getTraceableElement()
     * @generated
     */
		EClass TRACEABLE_ELEMENT = eINSTANCE.getTraceableElement();

		/**
     * The meta object literal for the '<em><b>Incoming Traces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TRACEABLE_ELEMENT__INCOMING_TRACES = eINSTANCE.getTraceableElement_IncomingTraces();

		/**
     * The meta object literal for the '<em><b>Outgoing Traces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TRACEABLE_ELEMENT__OUTGOING_TRACES = eINSTANCE.getTraceableElement_OutgoingTraces();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.FinalizableElementImpl <em>Finalizable Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.FinalizableElementImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getFinalizableElement()
     * @generated
     */
		EClass FINALIZABLE_ELEMENT = eINSTANCE.getFinalizableElement();

		/**
     * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute FINALIZABLE_ELEMENT__FINAL = eINSTANCE.getFinalizableElement_Final();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.PublishableElementImpl <em>Publishable Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.PublishableElementImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getPublishableElement()
     * @generated
     */
		EClass PUBLISHABLE_ELEMENT = eINSTANCE.getPublishableElement();

		/**
     * The meta object literal for the '<em><b>Visible In Doc</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC = eINSTANCE.getPublishableElement_VisibleInDoc();

		/**
     * The meta object literal for the '<em><b>Visible In LM</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PUBLISHABLE_ELEMENT__VISIBLE_IN_LM = eINSTANCE.getPublishableElement_VisibleInLM();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractTypeImpl <em>Abstract Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractTypeImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractType()
     * @generated
     */
		EClass ABSTRACT_TYPE = eINSTANCE.getAbstractType();

		/**
     * The meta object literal for the '<em><b>Abstract Typed Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS = eINSTANCE.getAbstractType_AbstractTypedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractTypedElementImpl <em>Abstract Typed Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractTypedElementImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractTypedElement()
     * @generated
     */
		EClass ABSTRACT_TYPED_ELEMENT = eINSTANCE.getAbstractTypedElement();

		/**
     * The meta object literal for the '<em><b>Abstract Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE = eINSTANCE.getAbstractTypedElement_AbstractType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace <em>Abstract Trace</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractTrace()
     * @generated
     */
		EClass ABSTRACT_TRACE = eINSTANCE.getAbstractTrace();

		/**
     * The meta object literal for the '<em><b>Target Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_TRACE__TARGET_ELEMENT = eINSTANCE.getAbstractTrace_TargetElement();

		/**
     * The meta object literal for the '<em><b>Source Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_TRACE__SOURCE_ELEMENT = eINSTANCE.getAbstractTrace_SourceElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint <em>Abstract Constraint</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.AbstractConstraint
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractConstraint()
     * @generated
     */
		EClass ABSTRACT_CONSTRAINT = eINSTANCE.getAbstractConstraint();

		/**
     * The meta object literal for the '<em><b>Constrained Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS = eINSTANCE.getAbstractConstraint_ConstrainedElements();

		/**
     * The meta object literal for the '<em><b>Owned Specification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION = eINSTANCE.getAbstractConstraint_OwnedSpecification();

		/**
     * The meta object literal for the '<em><b>Context</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CONSTRAINT__CONTEXT = eINSTANCE.getAbstractConstraint_Context();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.ValueSpecification <em>Value Specification</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.ValueSpecification
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getValueSpecification()
     * @generated
     */
		EClass VALUE_SPECIFICATION = eINSTANCE.getValueSpecification();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl <em>Abstract Parameter</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractParameter()
     * @generated
     */
		EClass ABSTRACT_PARAMETER = eINSTANCE.getAbstractParameter();

		/**
     * The meta object literal for the '<em><b>Is Exception</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_PARAMETER__IS_EXCEPTION = eINSTANCE.getAbstractParameter_IsException();

		/**
     * The meta object literal for the '<em><b>Is Stream</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_PARAMETER__IS_STREAM = eINSTANCE.getAbstractParameter_IsStream();

		/**
     * The meta object literal for the '<em><b>Is Optional</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_PARAMETER__IS_OPTIONAL = eINSTANCE.getAbstractParameter_IsOptional();

		/**
     * The meta object literal for the '<em><b>Kind Of Rate</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_PARAMETER__KIND_OF_RATE = eINSTANCE.getAbstractParameter_KindOfRate();

		/**
     * The meta object literal for the '<em><b>Effect</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_PARAMETER__EFFECT = eINSTANCE.getAbstractParameter_Effect();

		/**
     * The meta object literal for the '<em><b>Rate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PARAMETER__RATE = eINSTANCE.getAbstractParameter_Rate();

		/**
     * The meta object literal for the '<em><b>Probability</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PARAMETER__PROBABILITY = eINSTANCE.getAbstractParameter_Probability();

		/**
     * The meta object literal for the '<em><b>Parameter Set</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PARAMETER__PARAMETER_SET = eINSTANCE.getAbstractParameter_ParameterSet();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl <em>Abstract Parameter Set</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractParameterSet()
     * @generated
     */
		EClass ABSTRACT_PARAMETER_SET = eINSTANCE.getAbstractParameterSet();

		/**
     * The meta object literal for the '<em><b>Owned Conditions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS = eINSTANCE.getAbstractParameterSet_OwnedConditions();

		/**
     * The meta object literal for the '<em><b>Probability</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PARAMETER_SET__PROBABILITY = eINSTANCE.getAbstractParameterSet_Probability();

		/**
     * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PARAMETER_SET__PARAMETERS = eINSTANCE.getAbstractParameterSet_Parameters();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl <em>Abstract Information Flow</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractInformationFlow()
     * @generated
     */
		EClass ABSTRACT_INFORMATION_FLOW = eINSTANCE.getAbstractInformationFlow();

		/**
     * The meta object literal for the '<em><b>Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_INFORMATION_FLOW__REALIZATIONS = eINSTANCE.getAbstractInformationFlow_Realizations();

		/**
     * The meta object literal for the '<em><b>Convoyed Informations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS = eINSTANCE.getAbstractInformationFlow_ConvoyedInformations();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_INFORMATION_FLOW__SOURCE = eINSTANCE.getAbstractInformationFlow_Source();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_INFORMATION_FLOW__TARGET = eINSTANCE.getAbstractInformationFlow_Target();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractExchangeItemImpl <em>Abstract Exchange Item</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.impl.AbstractExchangeItemImpl
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getAbstractExchangeItem()
     * @generated
     */
		EClass ABSTRACT_EXCHANGE_ITEM = eINSTANCE.getAbstractExchangeItem();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.IState <em>IState</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.IState
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getIState()
     * @generated
     */
		EClass ISTATE = eINSTANCE.getIState();

		/**
     * The meta object literal for the '<em><b>Referenced States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ISTATE__REFERENCED_STATES = eINSTANCE.getIState_ReferencedStates();

		/**
     * The meta object literal for the '<em><b>Exploited States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ISTATE__EXPLOITED_STATES = eINSTANCE.getIState_ExploitedStates();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.ParameterEffectKind <em>Parameter Effect Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.ParameterEffectKind
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getParameterEffectKind()
     * @generated
     */
		EEnum PARAMETER_EFFECT_KIND = eINSTANCE.getParameterEffectKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.data.modellingcore.RateKind <em>Rate Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.data.modellingcore.RateKind
     * @see org.polarsys.capella.common.data.modellingcore.impl.ModellingcorePackageImpl#getRateKind()
     * @generated
     */
		EEnum RATE_KIND = eINSTANCE.getRateKind();

	}

} //ModellingcorePackage
