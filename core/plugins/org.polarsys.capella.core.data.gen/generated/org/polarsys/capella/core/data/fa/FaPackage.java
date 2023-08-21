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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.information.InformationPackage;

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
 * @see org.polarsys.capella.core.data.fa.FaFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='FunctionalAnalysis aims at defining the system engineering usual functional breakdown and functional data flow language (close to the UML Activity machine and SysML Activity as Block, partially).\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model CapellaCommon.ecore\r\nThis package depends on the model Information.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface FaPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "fa"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/fa/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.fa"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	FaPackage eINSTANCE = org.polarsys.capella.core.data.fa.impl.FaPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalArchitectureImpl <em>Abstract Functional Architecture</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionalArchitectureImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionalArchitecture()
   * @generated
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_EXTENSIONS = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__ID = CapellacorePackage.MODELLING_ARCHITECTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__SID = CapellacorePackage.MODELLING_ARCHITECTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__CONSTRAINTS = CapellacorePackage.MODELLING_ARCHITECTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_CONSTRAINTS = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__NAME = CapellacorePackage.MODELLING_ARCHITECTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__INCOMING_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OUTGOING_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__VISIBLE_IN_DOC = CapellacorePackage.MODELLING_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__VISIBLE_IN_LM = CapellacorePackage.MODELLING_ARCHITECTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__SUMMARY = CapellacorePackage.MODELLING_ARCHITECTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__DESCRIPTION = CapellacorePackage.MODELLING_ARCHITECTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__REVIEW = CapellacorePackage.MODELLING_ARCHITECTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_PROPERTY_VALUES = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__APPLIED_PROPERTY_VALUES = CapellacorePackage.MODELLING_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__STATUS = CapellacorePackage.MODELLING_ARCHITECTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__FEATURES = CapellacorePackage.MODELLING_ARCHITECTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__CONTAINED_GENERIC_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__NAMING_RULES = CapellacorePackage.MODELLING_ARCHITECTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.MODELLING_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Abstract Functional Architecture</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT = CapellacorePackage.MODELLING_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl <em>Abstract Functional Block</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionalBlock()
   * @generated
   */
	int ABSTRACT_FUNCTIONAL_BLOCK = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_EXTENSIONS = CapellacorePackage.MODELLING_BLOCK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__ID = CapellacorePackage.MODELLING_BLOCK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__SID = CapellacorePackage.MODELLING_BLOCK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__CONSTRAINTS = CapellacorePackage.MODELLING_BLOCK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_CONSTRAINTS = CapellacorePackage.MODELLING_BLOCK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.MODELLING_BLOCK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__NAME = CapellacorePackage.MODELLING_BLOCK__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.MODELLING_BLOCK__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__INCOMING_TRACES = CapellacorePackage.MODELLING_BLOCK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OUTGOING_TRACES = CapellacorePackage.MODELLING_BLOCK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__VISIBLE_IN_DOC = CapellacorePackage.MODELLING_BLOCK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__VISIBLE_IN_LM = CapellacorePackage.MODELLING_BLOCK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__SUMMARY = CapellacorePackage.MODELLING_BLOCK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__DESCRIPTION = CapellacorePackage.MODELLING_BLOCK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__REVIEW = CapellacorePackage.MODELLING_BLOCK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_PROPERTY_VALUES = CapellacorePackage.MODELLING_BLOCK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.MODELLING_BLOCK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__APPLIED_PROPERTY_VALUES = CapellacorePackage.MODELLING_BLOCK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_BLOCK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_BLOCK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__STATUS = CapellacorePackage.MODELLING_BLOCK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__FEATURES = CapellacorePackage.MODELLING_BLOCK__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_TRACES = CapellacorePackage.MODELLING_BLOCK__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__CONTAINED_GENERIC_TRACES = CapellacorePackage.MODELLING_BLOCK__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__NAMING_RULES = CapellacorePackage.MODELLING_BLOCK__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__TYPED_ELEMENTS = CapellacorePackage.MODELLING_BLOCK__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 6;

	/**
   * The number of structural features of the '<em>Abstract Functional Block</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_BLOCK_FEATURE_COUNT = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 7;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionPkgImpl <em>Function Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionPkgImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionPkg()
   * @generated
   */
	int FUNCTION_PKG = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_EXCHANGES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_CATEGORIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Function Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Function Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl <em>Function Specification</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionSpecification()
   * @generated
   */
	int FUNCTION_SPECIFICATION = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_EXTENSIONS = CapellacorePackage.NAMESPACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__ID = CapellacorePackage.NAMESPACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__SID = CapellacorePackage.NAMESPACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__CONSTRAINTS = CapellacorePackage.NAMESPACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMESPACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMESPACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__NAME = CapellacorePackage.NAMESPACE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__INCOMING_TRACES = CapellacorePackage.NAMESPACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OUTGOING_TRACES = CapellacorePackage.NAMESPACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__VISIBLE_IN_DOC = CapellacorePackage.NAMESPACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__VISIBLE_IN_LM = CapellacorePackage.NAMESPACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__SUMMARY = CapellacorePackage.NAMESPACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__DESCRIPTION = CapellacorePackage.NAMESPACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__REVIEW = CapellacorePackage.NAMESPACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__STATUS = CapellacorePackage.NAMESPACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__FEATURES = CapellacorePackage.NAMESPACE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_TRACES = CapellacorePackage.NAMESPACE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__CONTAINED_GENERIC_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__NAMING_RULES = CapellacorePackage.NAMESPACE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Is Control Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Parameter Set</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_PARAMETER = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__IS_READ_ONLY = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Is Single Execution</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Nodes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_NODES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Edges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_EDGES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_GROUPS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Structured Nodes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_STRUCTURED_NODES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Function Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Sub Function Specifications</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 12;

	/**
   * The number of structural features of the '<em>Function Specification</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_SPECIFICATION_FEATURE_COUNT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 13;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeCategoryImpl <em>Exchange Category</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ExchangeCategoryImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeCategory()
   * @generated
   */
	int EXCHANGE_CATEGORY = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY__EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Exchange Category</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CATEGORY_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeLinkImpl <em>Exchange Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ExchangeLinkImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeLink()
   * @generated
   */
	int EXCHANGE_LINK = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_EXTENSIONS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__ID = CapellacorePackage.NAMED_RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__SID = CapellacorePackage.NAMED_RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__REALIZED_FLOW = CapellacorePackage.NAMED_RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__INCOMING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OUTGOING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__VISIBLE_IN_DOC = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__VISIBLE_IN_LM = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__SUMMARY = CapellacorePackage.NAMED_RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__DESCRIPTION = CapellacorePackage.NAMED_RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__REVIEW = CapellacorePackage.NAMED_RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__STATUS = CapellacorePackage.NAMED_RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__FEATURES = CapellacorePackage.NAMED_RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__NAME = CapellacorePackage.NAMED_RELATIONSHIP__NAME;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__NAMING_RULES = CapellacorePackage.NAMED_RELATIONSHIP__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__EXCHANGES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Exchange Containment Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__EXCHANGE_CONTAINMENT_LINKS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Exchange Containments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__OWNED_EXCHANGE_CONTAINMENTS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Sources</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__SOURCES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Destinations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK__DESTINATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Exchange Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_LINK_FEATURE_COUNT = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeContainmentImpl <em>Exchange Containment</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ExchangeContainmentImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeContainment()
   * @generated
   */
	int EXCHANGE_CONTAINMENT = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__EXCHANGE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT__LINK = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Exchange Containment</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_CONTAINMENT_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationImpl <em>Exchange Specification</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeSpecification()
   * @generated
   */
	int EXCHANGE_SPECIFICATION = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__REALIZED_FLOW = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Convoyed Informations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__CONVOYED_INFORMATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__SOURCE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__TARGET = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Realizing Activity Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__REALIZING_ACTIVITY_FLOWS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Containing Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__CONTAINING_LINK = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__LINK = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Outgoing Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Incoming Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The number of structural features of the '<em>Exchange Specification</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeSpecificationImpl <em>Functional Exchange Specification</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalExchangeSpecificationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalExchangeSpecification()
   * @generated
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OWNED_EXTENSIONS = EXCHANGE_SPECIFICATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__ID = EXCHANGE_SPECIFICATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__SID = EXCHANGE_SPECIFICATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__CONSTRAINTS = EXCHANGE_SPECIFICATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OWNED_CONSTRAINTS = EXCHANGE_SPECIFICATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OWNED_MIGRATED_ELEMENTS = EXCHANGE_SPECIFICATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__NAME = EXCHANGE_SPECIFICATION__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__INCOMING_TRACES = EXCHANGE_SPECIFICATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OUTGOING_TRACES = EXCHANGE_SPECIFICATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__VISIBLE_IN_DOC = EXCHANGE_SPECIFICATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__VISIBLE_IN_LM = EXCHANGE_SPECIFICATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__SUMMARY = EXCHANGE_SPECIFICATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__DESCRIPTION = EXCHANGE_SPECIFICATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__REVIEW = EXCHANGE_SPECIFICATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OWNED_PROPERTY_VALUES = EXCHANGE_SPECIFICATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OWNED_ENUMERATION_PROPERTY_TYPES = EXCHANGE_SPECIFICATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__APPLIED_PROPERTY_VALUES = EXCHANGE_SPECIFICATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OWNED_PROPERTY_VALUE_GROUPS = EXCHANGE_SPECIFICATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__APPLIED_PROPERTY_VALUE_GROUPS = EXCHANGE_SPECIFICATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__STATUS = EXCHANGE_SPECIFICATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__FEATURES = EXCHANGE_SPECIFICATION__FEATURES;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__REALIZED_FLOW = EXCHANGE_SPECIFICATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__REALIZATIONS = EXCHANGE_SPECIFICATION__REALIZATIONS;

	/**
   * The feature id for the '<em><b>Convoyed Informations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__CONVOYED_INFORMATIONS = EXCHANGE_SPECIFICATION__CONVOYED_INFORMATIONS;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__SOURCE = EXCHANGE_SPECIFICATION__SOURCE;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__TARGET = EXCHANGE_SPECIFICATION__TARGET;

	/**
   * The feature id for the '<em><b>Realizing Activity Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__REALIZING_ACTIVITY_FLOWS = EXCHANGE_SPECIFICATION__REALIZING_ACTIVITY_FLOWS;

	/**
   * The feature id for the '<em><b>Containing Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__CONTAINING_LINK = EXCHANGE_SPECIFICATION__CONTAINING_LINK;

	/**
   * The feature id for the '<em><b>Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__LINK = EXCHANGE_SPECIFICATION__LINK;

	/**
   * The feature id for the '<em><b>Outgoing Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS = EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Incoming Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS = EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION__FUNCTIONAL_EXCHANGES = EXCHANGE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Functional Exchange Specification</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_SPECIFICATION_FEATURE_COUNT = EXCHANGE_SPECIFICATION_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl <em>Functional Chain</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChain()
   * @generated
   */
	int FUNCTIONAL_CHAIN = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVED_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVING_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__KIND = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Involved Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Involved Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Involved Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Enacted Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Enacted Functional Blocks</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>First Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Realized Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Realizing Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Pre Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__PRE_CONDITION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Post Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__POST_CONDITION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Owned Sequence Nodes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Owned Sequence Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 20;

	/**
   * The number of structural features of the '<em>Functional Chain</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 21;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalChainContainerImpl <em>Abstract Functional Chain Container</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionalChainContainerImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionalChainContainer()
   * @generated
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Functional Chain Container</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl <em>Functional Chain Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainInvolvement()
   * @generated
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Next Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Previous Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Functional Chain Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainReferenceImpl <em>Functional Chain Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainReferenceImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainReference()
   * @generated
   */
	int FUNCTIONAL_CHAIN_REFERENCE = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OWNED_EXTENSIONS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__ID = FUNCTIONAL_CHAIN_INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__SID = FUNCTIONAL_CHAIN_INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__CONSTRAINTS = FUNCTIONAL_CHAIN_INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OWNED_CONSTRAINTS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OWNED_MIGRATED_ELEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__REALIZED_FLOW = FUNCTIONAL_CHAIN_INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__INCOMING_TRACES = FUNCTIONAL_CHAIN_INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OUTGOING_TRACES = FUNCTIONAL_CHAIN_INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__VISIBLE_IN_DOC = FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__VISIBLE_IN_LM = FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__SUMMARY = FUNCTIONAL_CHAIN_INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__DESCRIPTION = FUNCTIONAL_CHAIN_INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__REVIEW = FUNCTIONAL_CHAIN_INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OWNED_PROPERTY_VALUES = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__APPLIED_PROPERTY_VALUES = FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__STATUS = FUNCTIONAL_CHAIN_INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__FEATURES = FUNCTIONAL_CHAIN_INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__INVOLVER = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__INVOLVED = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Next Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Previous Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__INVOLVED_ELEMENT = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT;

	/**
   * The feature id for the '<em><b>Referenced Functional Chain</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Functional Chain Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REFERENCE_FEATURE_COUNT = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl <em>Function Port</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionPortImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionPort()
   * @generated
   */
	int FUNCTION_PORT = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_EXTENSIONS = InformationPackage.PORT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__ID = InformationPackage.PORT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__SID = InformationPackage.PORT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__CONSTRAINTS = InformationPackage.PORT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_CONSTRAINTS = InformationPackage.PORT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_MIGRATED_ELEMENTS = InformationPackage.PORT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__NAME = InformationPackage.PORT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__INCOMING_TRACES = InformationPackage.PORT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OUTGOING_TRACES = InformationPackage.PORT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__VISIBLE_IN_DOC = InformationPackage.PORT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__VISIBLE_IN_LM = InformationPackage.PORT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__SUMMARY = InformationPackage.PORT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__DESCRIPTION = InformationPackage.PORT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__REVIEW = InformationPackage.PORT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_PROPERTY_VALUES = InformationPackage.PORT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_ENUMERATION_PROPERTY_TYPES = InformationPackage.PORT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__APPLIED_PROPERTY_VALUES = InformationPackage.PORT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_PROPERTY_VALUE_GROUPS = InformationPackage.PORT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__APPLIED_PROPERTY_VALUE_GROUPS = InformationPackage.PORT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__STATUS = InformationPackage.PORT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__FEATURES = InformationPackage.PORT__FEATURES;

	/**
   * The feature id for the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__INCOMING_PORT_REALIZATIONS = InformationPackage.PORT__INCOMING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OUTGOING_PORT_REALIZATIONS = InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Protocols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_PROTOCOLS = InformationPackage.PORT__OWNED_PROTOCOLS;

	/**
   * The feature id for the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__INCOMING_PORT_ALLOCATIONS = InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OUTGOING_PORT_ALLOCATIONS = InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__PROVIDED_INTERFACES = InformationPackage.PORT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__REQUIRED_INTERFACES = InformationPackage.PORT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_PORT_REALIZATIONS = InformationPackage.PORT__OWNED_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__OWNED_PORT_ALLOCATIONS = InformationPackage.PORT__OWNED_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__ABSTRACT_TYPE = InformationPackage.PORT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__TYPE = InformationPackage.PORT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS = InformationPackage.PORT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Represented Component Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	int FUNCTION_PORT__REPRESENTED_COMPONENT_PORT = InformationPackage.PORT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocator Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS = InformationPackage.PORT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Realized Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__REALIZED_FUNCTION_PORTS = InformationPackage.PORT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Realizing Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT__REALIZING_FUNCTION_PORTS = InformationPackage.PORT_FEATURE_COUNT + 6;

	/**
   * The number of structural features of the '<em>Function Port</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_PORT_FEATURE_COUNT = InformationPackage.PORT_FEATURE_COUNT + 7;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionInputPortImpl <em>Function Input Port</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionInputPortImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionInputPort()
   * @generated
   */
	int FUNCTION_INPUT_PORT = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_EXTENSIONS = FUNCTION_PORT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__ID = FUNCTION_PORT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__SID = FUNCTION_PORT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__CONSTRAINTS = FUNCTION_PORT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_CONSTRAINTS = FUNCTION_PORT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_MIGRATED_ELEMENTS = FUNCTION_PORT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__NAME = FUNCTION_PORT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INCOMING_TRACES = FUNCTION_PORT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OUTGOING_TRACES = FUNCTION_PORT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__VISIBLE_IN_DOC = FUNCTION_PORT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__VISIBLE_IN_LM = FUNCTION_PORT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__SUMMARY = FUNCTION_PORT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__DESCRIPTION = FUNCTION_PORT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__REVIEW = FUNCTION_PORT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_PROPERTY_VALUES = FUNCTION_PORT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_ENUMERATION_PROPERTY_TYPES = FUNCTION_PORT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__APPLIED_PROPERTY_VALUES = FUNCTION_PORT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_PROPERTY_VALUE_GROUPS = FUNCTION_PORT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__APPLIED_PROPERTY_VALUE_GROUPS = FUNCTION_PORT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__STATUS = FUNCTION_PORT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__FEATURES = FUNCTION_PORT__FEATURES;

	/**
   * The feature id for the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INCOMING_PORT_REALIZATIONS = FUNCTION_PORT__INCOMING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OUTGOING_PORT_REALIZATIONS = FUNCTION_PORT__OUTGOING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Protocols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_PROTOCOLS = FUNCTION_PORT__OWNED_PROTOCOLS;

	/**
   * The feature id for the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INCOMING_PORT_ALLOCATIONS = FUNCTION_PORT__INCOMING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OUTGOING_PORT_ALLOCATIONS = FUNCTION_PORT__OUTGOING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__PROVIDED_INTERFACES = FUNCTION_PORT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__REQUIRED_INTERFACES = FUNCTION_PORT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_PORT_REALIZATIONS = FUNCTION_PORT__OWNED_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OWNED_PORT_ALLOCATIONS = FUNCTION_PORT__OWNED_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__ABSTRACT_TYPE = FUNCTION_PORT__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__TYPE = FUNCTION_PORT__TYPE;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__ABSTRACT_TYPED_ELEMENTS = FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Represented Component Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	int FUNCTION_INPUT_PORT__REPRESENTED_COMPONENT_PORT = FUNCTION_PORT__REPRESENTED_COMPONENT_PORT;

	/**
   * The feature id for the '<em><b>Allocator Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__ALLOCATOR_COMPONENT_PORTS = FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Realized Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__REALIZED_FUNCTION_PORTS = FUNCTION_PORT__REALIZED_FUNCTION_PORTS;

	/**
   * The feature id for the '<em><b>Realizing Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__REALIZING_FUNCTION_PORTS = FUNCTION_PORT__REALIZING_FUNCTION_PORTS;

	/**
   * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__IN_ACTIVITY_PARTITION = FUNCTION_PORT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__IN_INTERRUPTIBLE_REGION = FUNCTION_PORT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>In Structured Node</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__IN_STRUCTURED_NODE = FUNCTION_PORT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__OUTGOING = FUNCTION_PORT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INCOMING = FUNCTION_PORT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Is Control Type</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__IS_CONTROL_TYPE = FUNCTION_PORT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Kind Of Node</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__KIND_OF_NODE = FUNCTION_PORT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Ordering</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__ORDERING = FUNCTION_PORT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Upper Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__UPPER_BOUND = FUNCTION_PORT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>In State</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__IN_STATE = FUNCTION_PORT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Selection</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__SELECTION = FUNCTION_PORT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Is Control</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__IS_CONTROL = FUNCTION_PORT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Input Evaluation Action</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INPUT_EVALUATION_ACTION = FUNCTION_PORT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Incoming Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS = FUNCTION_PORT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Incoming Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT__INCOMING_FUNCTIONAL_EXCHANGES = FUNCTION_PORT_FEATURE_COUNT + 14;

	/**
   * The number of structural features of the '<em>Function Input Port</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_INPUT_PORT_FEATURE_COUNT = FUNCTION_PORT_FEATURE_COUNT + 15;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionOutputPortImpl <em>Function Output Port</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionOutputPortImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionOutputPort()
   * @generated
   */
	int FUNCTION_OUTPUT_PORT = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_EXTENSIONS = FUNCTION_PORT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__ID = FUNCTION_PORT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__SID = FUNCTION_PORT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__CONSTRAINTS = FUNCTION_PORT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_CONSTRAINTS = FUNCTION_PORT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_MIGRATED_ELEMENTS = FUNCTION_PORT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__NAME = FUNCTION_PORT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__INCOMING_TRACES = FUNCTION_PORT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OUTGOING_TRACES = FUNCTION_PORT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__VISIBLE_IN_DOC = FUNCTION_PORT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__VISIBLE_IN_LM = FUNCTION_PORT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__SUMMARY = FUNCTION_PORT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__DESCRIPTION = FUNCTION_PORT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__REVIEW = FUNCTION_PORT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_PROPERTY_VALUES = FUNCTION_PORT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_ENUMERATION_PROPERTY_TYPES = FUNCTION_PORT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__APPLIED_PROPERTY_VALUES = FUNCTION_PORT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_PROPERTY_VALUE_GROUPS = FUNCTION_PORT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__APPLIED_PROPERTY_VALUE_GROUPS = FUNCTION_PORT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__STATUS = FUNCTION_PORT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__FEATURES = FUNCTION_PORT__FEATURES;

	/**
   * The feature id for the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__INCOMING_PORT_REALIZATIONS = FUNCTION_PORT__INCOMING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OUTGOING_PORT_REALIZATIONS = FUNCTION_PORT__OUTGOING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Protocols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_PROTOCOLS = FUNCTION_PORT__OWNED_PROTOCOLS;

	/**
   * The feature id for the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__INCOMING_PORT_ALLOCATIONS = FUNCTION_PORT__INCOMING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OUTGOING_PORT_ALLOCATIONS = FUNCTION_PORT__OUTGOING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__PROVIDED_INTERFACES = FUNCTION_PORT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__REQUIRED_INTERFACES = FUNCTION_PORT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_PORT_REALIZATIONS = FUNCTION_PORT__OWNED_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OWNED_PORT_ALLOCATIONS = FUNCTION_PORT__OWNED_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__ABSTRACT_TYPE = FUNCTION_PORT__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__TYPE = FUNCTION_PORT__TYPE;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__ABSTRACT_TYPED_ELEMENTS = FUNCTION_PORT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Represented Component Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	int FUNCTION_OUTPUT_PORT__REPRESENTED_COMPONENT_PORT = FUNCTION_PORT__REPRESENTED_COMPONENT_PORT;

	/**
   * The feature id for the '<em><b>Allocator Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__ALLOCATOR_COMPONENT_PORTS = FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Realized Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__REALIZED_FUNCTION_PORTS = FUNCTION_PORT__REALIZED_FUNCTION_PORTS;

	/**
   * The feature id for the '<em><b>Realizing Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__REALIZING_FUNCTION_PORTS = FUNCTION_PORT__REALIZING_FUNCTION_PORTS;

	/**
   * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__IN_ACTIVITY_PARTITION = FUNCTION_PORT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__IN_INTERRUPTIBLE_REGION = FUNCTION_PORT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>In Structured Node</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__IN_STRUCTURED_NODE = FUNCTION_PORT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OUTGOING = FUNCTION_PORT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__INCOMING = FUNCTION_PORT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Is Control Type</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__IS_CONTROL_TYPE = FUNCTION_PORT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Kind Of Node</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__KIND_OF_NODE = FUNCTION_PORT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Ordering</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__ORDERING = FUNCTION_PORT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Upper Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__UPPER_BOUND = FUNCTION_PORT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>In State</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__IN_STATE = FUNCTION_PORT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Selection</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__SELECTION = FUNCTION_PORT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Is Control</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__IS_CONTROL = FUNCTION_PORT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Outgoing Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS = FUNCTION_PORT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Outgoing Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT__OUTGOING_FUNCTIONAL_EXCHANGES = FUNCTION_PORT_FEATURE_COUNT + 13;

	/**
   * The number of structural features of the '<em>Function Output Port</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_OUTPUT_PORT_FEATURE_COUNT = FUNCTION_PORT_FEATURE_COUNT + 14;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.AbstractFunctionAllocation <em>Abstract Function Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionAllocation
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionAllocation()
   * @generated
   */
	int ABSTRACT_FUNCTION_ALLOCATION = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Abstract Function Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentFunctionalAllocationImpl <em>Component Functional Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentFunctionalAllocationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentFunctionalAllocation()
   * @generated
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OWNED_EXTENSIONS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__ID = ABSTRACT_FUNCTION_ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__SID = ABSTRACT_FUNCTION_ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__CONSTRAINTS = ABSTRACT_FUNCTION_ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OWNED_CONSTRAINTS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__REALIZED_FLOW = ABSTRACT_FUNCTION_ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__INCOMING_TRACES = ABSTRACT_FUNCTION_ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OUTGOING_TRACES = ABSTRACT_FUNCTION_ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__VISIBLE_IN_DOC = ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__VISIBLE_IN_LM = ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__SUMMARY = ABSTRACT_FUNCTION_ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__DESCRIPTION = ABSTRACT_FUNCTION_ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__REVIEW = ABSTRACT_FUNCTION_ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OWNED_PROPERTY_VALUES = ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_FUNCTION_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__APPLIED_PROPERTY_VALUES = ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__STATUS = ABSTRACT_FUNCTION_ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__FEATURES = ABSTRACT_FUNCTION_ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__TARGET_ELEMENT = ABSTRACT_FUNCTION_ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__SOURCE_ELEMENT = ABSTRACT_FUNCTION_ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__FUNCTION = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Block</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION__BLOCK = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Component Functional Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FUNCTIONAL_ALLOCATION_FEATURE_COUNT = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainRealizationImpl <em>Functional Chain Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainRealizationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainRealization()
   * @generated
   */
	int FUNCTIONAL_CHAIN_REALIZATION = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Functional Chain Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationRealizationImpl <em>Exchange Specification Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationRealizationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeSpecificationRealization()
   * @generated
   */
	int EXCHANGE_SPECIFICATION_REALIZATION = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Exchange Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Exchange Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Exchange Specification Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_SPECIFICATION_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeRealizationImpl <em>Functional Exchange Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalExchangeRealizationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalExchangeRealization()
   * @generated
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Functional Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Functional Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Functional Exchange Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionRealizationImpl <em>Function Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionRealizationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionRealization()
   * @generated
   */
	int FUNCTION_REALIZATION = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OWNED_EXTENSIONS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__ID = ABSTRACT_FUNCTION_ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__SID = ABSTRACT_FUNCTION_ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__CONSTRAINTS = ABSTRACT_FUNCTION_ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OWNED_CONSTRAINTS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__REALIZED_FLOW = ABSTRACT_FUNCTION_ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__INCOMING_TRACES = ABSTRACT_FUNCTION_ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OUTGOING_TRACES = ABSTRACT_FUNCTION_ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__VISIBLE_IN_DOC = ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__VISIBLE_IN_LM = ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__SUMMARY = ABSTRACT_FUNCTION_ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__DESCRIPTION = ABSTRACT_FUNCTION_ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__REVIEW = ABSTRACT_FUNCTION_ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OWNED_PROPERTY_VALUES = ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_FUNCTION_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__APPLIED_PROPERTY_VALUES = ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__STATUS = ABSTRACT_FUNCTION_ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__FEATURES = ABSTRACT_FUNCTION_ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__TARGET_ELEMENT = ABSTRACT_FUNCTION_ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__SOURCE_ELEMENT = ABSTRACT_FUNCTION_ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__ALLOCATED_FUNCTION = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION__ALLOCATING_FUNCTION = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Function Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTION_REALIZATION_FEATURE_COUNT = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeImpl <em>Functional Exchange</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalExchangeImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalExchange()
   * @generated
   */
	int FUNCTIONAL_EXCHANGE = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__REALIZED_FLOW = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INVOLVING_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Kind Of Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__KIND_OF_RATE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__IN_ACTIVITY_PARTITION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__IN_INTERRUPTIBLE_REGION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>In Structured Node</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__IN_STRUCTURED_NODE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Rate</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__RATE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Probability</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__PROBABILITY = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__TARGET = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__SOURCE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__GUARD = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Weight</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__WEIGHT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Interrupts</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INTERRUPTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Is Multicast</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__IS_MULTICAST = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Is Multireceive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__IS_MULTIRECEIVE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Transformation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__TRANSFORMATION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Selection</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__SELECTION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INVOKING_SEQUENCE_MESSAGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Exchange Specifications</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__EXCHANGE_SPECIFICATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Involving Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INVOLVING_FUNCTIONAL_CHAINS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Exchanged Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Allocating Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__ALLOCATING_COMPONENT_EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Incoming Component Exchange Functional Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Incoming Functional Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__INCOMING_FUNCTIONAL_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Outgoing Functional Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OUTGOING_FUNCTIONAL_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Categories</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__CATEGORIES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Owned Functional Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Source Function Output Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Target Function Input Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Realized Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__REALIZED_FUNCTIONAL_EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Realizing Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE__REALIZING_FUNCTIONAL_EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 31;

	/**
   * The number of structural features of the '<em>Functional Exchange</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_EXCHANGE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 32;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl <em>Abstract Function</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunction()
   * @generated
   */
	int ABSTRACT_FUNCTION = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_EXTENSIONS = CapellacorePackage.NAMESPACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ID = CapellacorePackage.NAMESPACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__SID = CapellacorePackage.NAMESPACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__CONSTRAINTS = CapellacorePackage.NAMESPACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_CONSTRAINTS = CapellacorePackage.NAMESPACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMESPACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__NAME = CapellacorePackage.NAMESPACE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INCOMING_TRACES = CapellacorePackage.NAMESPACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OUTGOING_TRACES = CapellacorePackage.NAMESPACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__VISIBLE_IN_DOC = CapellacorePackage.NAMESPACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__VISIBLE_IN_LM = CapellacorePackage.NAMESPACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__SUMMARY = CapellacorePackage.NAMESPACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__DESCRIPTION = CapellacorePackage.NAMESPACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__REVIEW = CapellacorePackage.NAMESPACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__STATUS = CapellacorePackage.NAMESPACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__FEATURES = CapellacorePackage.NAMESPACE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_TRACES = CapellacorePackage.NAMESPACE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__CONTAINED_GENERIC_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__NAMING_RULES = CapellacorePackage.NAMESPACE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INVOLVING_INVOLVEMENTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IS_ABSTRACT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IS_STATIC = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__VISIBILITY = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ABSTRACT_TYPE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__TYPE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ORDERED = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__UNIQUE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__MIN_INCLUSIVE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__MAX_INCLUSIVE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_DEFAULT_VALUE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MIN_VALUE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MAX_VALUE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_NULL_VALUE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MIN_CARD = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MIN_LENGTH = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MAX_CARD = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_MAX_LENGTH = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__FINAL = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__AGGREGATION_KIND = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IS_DERIVED = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IS_READ_ONLY = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IS_PART_OF_KEY = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ASSOCIATION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__REPRESENTING_INSTANCE_ROLES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_CHAINS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IN_ACTIVITY_PARTITION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IN_INTERRUPTIBLE_REGION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>In Structured Node</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IN_STRUCTURED_NODE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OUTGOING = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INCOMING = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Owned Handlers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_HANDLERS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Local Precondition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__LOCAL_PRECONDITION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 32;

	/**
   * The feature id for the '<em><b>Local Postcondition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__LOCAL_POSTCONDITION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 33;

	/**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__CONTEXT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 34;

	/**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INPUTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 35;

	/**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OUTPUTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 36;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ARGUMENTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 37;

	/**
   * The feature id for the '<em><b>Results</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__RESULTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 38;

	/**
   * The feature id for the '<em><b>Behavior</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__BEHAVIOR = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 39;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 40;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__KIND = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 41;

	/**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__CONDITION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 42;

	/**
   * The feature id for the '<em><b>Owned Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_FUNCTIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 43;

	/**
   * The feature id for the '<em><b>Owned Function Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 44;

	/**
   * The feature id for the '<em><b>Owned Functional Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 45;

	/**
   * The feature id for the '<em><b>Sub Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__SUB_FUNCTIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 46;

	/**
   * The feature id for the '<em><b>Out Function Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 47;

	/**
   * The feature id for the '<em><b>In Function Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 48;

	/**
   * The feature id for the '<em><b>Component Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 49;

	/**
   * The feature id for the '<em><b>Allocation Blocks</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__ALLOCATION_BLOCKS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 50;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__AVAILABLE_IN_STATES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 51;

	/**
   * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 52;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 53;

	/**
   * The feature id for the '<em><b>Involving Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 54;

	/**
   * The feature id for the '<em><b>Linked State Machine</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__LINKED_STATE_MACHINE = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 55;

	/**
   * The feature id for the '<em><b>Linked Function Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 56;

	/**
   * The number of structural features of the '<em>Abstract Function</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_FEATURE_COUNT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 57;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeImpl <em>Component Exchange</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchange()
   * @generated
   */
	int COMPONENT_EXCHANGE = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_EXTENSIONS = BehaviorPackage.ABSTRACT_EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__ID = BehaviorPackage.ABSTRACT_EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__SID = BehaviorPackage.ABSTRACT_EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__CONSTRAINTS = BehaviorPackage.ABSTRACT_EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_CONSTRAINTS = BehaviorPackage.ABSTRACT_EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_MIGRATED_ELEMENTS = BehaviorPackage.ABSTRACT_EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__NAME = BehaviorPackage.ABSTRACT_EVENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__ABSTRACT_TYPED_ELEMENTS = BehaviorPackage.ABSTRACT_EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__INCOMING_TRACES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OUTGOING_TRACES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__VISIBLE_IN_DOC = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__VISIBLE_IN_LM = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__SUMMARY = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__DESCRIPTION = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__REVIEW = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_PROPERTY_VALUES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_ENUMERATION_PROPERTY_TYPES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__APPLIED_PROPERTY_VALUES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_PROPERTY_VALUE_GROUPS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__APPLIED_PROPERTY_VALUE_GROUPS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__STATUS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__FEATURES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__INVOKING_SEQUENCE_MESSAGES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__REALIZED_FLOW = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__REALIZATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Convoyed Informations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__CONVOYED_INFORMATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__SOURCE = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__TARGET = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Realizing Activity Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__REALIZING_ACTIVITY_FLOWS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Containing Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__CONTAINING_LINK = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__LINK = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Outgoing Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Incoming Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__KIND = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Oriented</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__ORIENTED = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Allocated Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__ALLOCATED_FUNCTIONAL_EXCHANGES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Incoming Component Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_REALIZATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Outgoing Component Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_REALIZATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Outgoing Component Exchange Functional Exchange Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Functional Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 32;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Ends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 33;

	/**
   * The feature id for the '<em><b>Source Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__SOURCE_PORT = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 34;

	/**
   * The feature id for the '<em><b>Source Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__SOURCE_PART = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 35;

	/**
   * The feature id for the '<em><b>Target Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__TARGET_PORT = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 36;

	/**
   * The feature id for the '<em><b>Target Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__TARGET_PART = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 37;

	/**
   * The feature id for the '<em><b>Categories</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__CATEGORIES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 38;

	/**
   * The feature id for the '<em><b>Allocator Physical Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__ALLOCATOR_PHYSICAL_LINKS = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 39;

	/**
   * The feature id for the '<em><b>Realized Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__REALIZED_COMPONENT_EXCHANGES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 40;

	/**
   * The feature id for the '<em><b>Realizing Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE__REALIZING_COMPONENT_EXCHANGES = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 41;

	/**
   * The number of structural features of the '<em>Component Exchange</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FEATURE_COUNT = BehaviorPackage.ABSTRACT_EVENT_FEATURE_COUNT + 42;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocationImpl <em>Component Exchange Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeAllocation()
   * @generated
   */
	int COMPONENT_EXCHANGE_ALLOCATION = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Component Exchange Allocated</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Component Exchange Allocator</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Component Exchange Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocatorImpl <em>Component Exchange Allocator</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocatorImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeAllocator()
   * @generated
   */
	int COMPONENT_EXCHANGE_ALLOCATOR = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocated Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR__ALLOCATED_COMPONENT_EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Component Exchange Allocator</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_ALLOCATOR_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeCategoryImpl <em>Component Exchange Category</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeCategoryImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeCategory()
   * @generated
   */
	int COMPONENT_EXCHANGE_CATEGORY = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY__EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Component Exchange Category</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_CATEGORY_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeEndImpl <em>Component Exchange End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeEndImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeEnd()
   * @generated
   */
	int COMPONENT_EXCHANGE_END = 28;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OWNED_EXTENSIONS = ModellingcorePackage.INFORMATIONS_EXCHANGER__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__ID = ModellingcorePackage.INFORMATIONS_EXCHANGER__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__SID = ModellingcorePackage.INFORMATIONS_EXCHANGER__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__CONSTRAINTS = ModellingcorePackage.INFORMATIONS_EXCHANGER__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OWNED_CONSTRAINTS = ModellingcorePackage.INFORMATIONS_EXCHANGER__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.INFORMATIONS_EXCHANGER__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__INCOMING_INFORMATION_FLOWS = ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS;

	/**
   * The feature id for the '<em><b>Outgoing Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OUTGOING_INFORMATION_FLOWS = ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS;

	/**
   * The feature id for the '<em><b>Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__INFORMATION_FLOWS = ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__INCOMING_TRACES = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OUTGOING_TRACES = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__VISIBLE_IN_DOC = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__VISIBLE_IN_LM = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__SUMMARY = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__DESCRIPTION = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__REVIEW = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OWNED_PROPERTY_VALUES = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__APPLIED_PROPERTY_VALUES = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__STATUS = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__FEATURES = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__PORT = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END__PART = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 15;

	/**
   * The number of structural features of the '<em>Component Exchange End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_END_FEATURE_COUNT = ModellingcorePackage.INFORMATIONS_EXCHANGER_FEATURE_COUNT + 16;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeFunctionalExchangeAllocationImpl <em>Component Exchange Functional Exchange Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeFunctionalExchangeAllocationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeFunctionalExchangeAllocation()
   * @generated
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION = 29;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OWNED_EXTENSIONS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ID = ABSTRACT_FUNCTION_ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__SID = ABSTRACT_FUNCTION_ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__CONSTRAINTS = ABSTRACT_FUNCTION_ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OWNED_CONSTRAINTS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__REALIZED_FLOW = ABSTRACT_FUNCTION_ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__INCOMING_TRACES = ABSTRACT_FUNCTION_ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OUTGOING_TRACES = ABSTRACT_FUNCTION_ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__VISIBLE_IN_DOC = ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__VISIBLE_IN_LM = ABSTRACT_FUNCTION_ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__SUMMARY = ABSTRACT_FUNCTION_ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__DESCRIPTION = ABSTRACT_FUNCTION_ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__REVIEW = ABSTRACT_FUNCTION_ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OWNED_PROPERTY_VALUES = ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_FUNCTION_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__APPLIED_PROPERTY_VALUES = ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_FUNCTION_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_FUNCTION_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__STATUS = ABSTRACT_FUNCTION_ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__FEATURES = ABSTRACT_FUNCTION_ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__TARGET_ELEMENT = ABSTRACT_FUNCTION_ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__SOURCE_ELEMENT = ABSTRACT_FUNCTION_ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Functional Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATED_FUNCTIONAL_EXCHANGE = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Component Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATING_COMPONENT_EXCHANGE = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Component Exchange Functional Exchange Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION_FEATURE_COUNT = ABSTRACT_FUNCTION_ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeRealizationImpl <em>Component Exchange Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeRealizationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeRealization()
   * @generated
   */
	int COMPONENT_EXCHANGE_REALIZATION = 30;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OWNED_EXTENSIONS = EXCHANGE_SPECIFICATION_REALIZATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__ID = EXCHANGE_SPECIFICATION_REALIZATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__SID = EXCHANGE_SPECIFICATION_REALIZATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__CONSTRAINTS = EXCHANGE_SPECIFICATION_REALIZATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OWNED_CONSTRAINTS = EXCHANGE_SPECIFICATION_REALIZATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OWNED_MIGRATED_ELEMENTS = EXCHANGE_SPECIFICATION_REALIZATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__REALIZED_FLOW = EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__INCOMING_TRACES = EXCHANGE_SPECIFICATION_REALIZATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OUTGOING_TRACES = EXCHANGE_SPECIFICATION_REALIZATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__VISIBLE_IN_DOC = EXCHANGE_SPECIFICATION_REALIZATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__VISIBLE_IN_LM = EXCHANGE_SPECIFICATION_REALIZATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__SUMMARY = EXCHANGE_SPECIFICATION_REALIZATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__DESCRIPTION = EXCHANGE_SPECIFICATION_REALIZATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__REVIEW = EXCHANGE_SPECIFICATION_REALIZATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OWNED_PROPERTY_VALUES = EXCHANGE_SPECIFICATION_REALIZATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = EXCHANGE_SPECIFICATION_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__APPLIED_PROPERTY_VALUES = EXCHANGE_SPECIFICATION_REALIZATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = EXCHANGE_SPECIFICATION_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = EXCHANGE_SPECIFICATION_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__STATUS = EXCHANGE_SPECIFICATION_REALIZATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__FEATURES = EXCHANGE_SPECIFICATION_REALIZATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__TARGET_ELEMENT = EXCHANGE_SPECIFICATION_REALIZATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__SOURCE_ELEMENT = EXCHANGE_SPECIFICATION_REALIZATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Exchange Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION = EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION;

	/**
   * The feature id for the '<em><b>Realizing Exchange Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION = EXCHANGE_SPECIFICATION_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION;

	/**
   * The feature id for the '<em><b>Allocated Component Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__ALLOCATED_COMPONENT_EXCHANGE = EXCHANGE_SPECIFICATION_REALIZATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Component Exchange</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION__ALLOCATING_COMPONENT_EXCHANGE = EXCHANGE_SPECIFICATION_REALIZATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Component Exchange Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_EXCHANGE_REALIZATION_FEATURE_COUNT = EXCHANGE_SPECIFICATION_REALIZATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentPortImpl <em>Component Port</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentPortImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPort()
   * @generated
   */
	int COMPONENT_PORT = 31;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_EXTENSIONS = InformationPackage.PORT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ID = InformationPackage.PORT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__SID = InformationPackage.PORT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__CONSTRAINTS = InformationPackage.PORT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_CONSTRAINTS = InformationPackage.PORT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MIGRATED_ELEMENTS = InformationPackage.PORT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__NAME = InformationPackage.PORT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__INCOMING_TRACES = InformationPackage.PORT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OUTGOING_TRACES = InformationPackage.PORT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__VISIBLE_IN_DOC = InformationPackage.PORT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__VISIBLE_IN_LM = InformationPackage.PORT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__SUMMARY = InformationPackage.PORT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__DESCRIPTION = InformationPackage.PORT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__REVIEW = InformationPackage.PORT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_PROPERTY_VALUES = InformationPackage.PORT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_ENUMERATION_PROPERTY_TYPES = InformationPackage.PORT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__APPLIED_PROPERTY_VALUES = InformationPackage.PORT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_PROPERTY_VALUE_GROUPS = InformationPackage.PORT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__APPLIED_PROPERTY_VALUE_GROUPS = InformationPackage.PORT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__STATUS = InformationPackage.PORT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__FEATURES = InformationPackage.PORT__FEATURES;

	/**
   * The feature id for the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__INCOMING_PORT_REALIZATIONS = InformationPackage.PORT__INCOMING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OUTGOING_PORT_REALIZATIONS = InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Protocols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_PROTOCOLS = InformationPackage.PORT__OWNED_PROTOCOLS;

	/**
   * The feature id for the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__INCOMING_PORT_ALLOCATIONS = InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OUTGOING_PORT_ALLOCATIONS = InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__PROVIDED_INTERFACES = InformationPackage.PORT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__REQUIRED_INTERFACES = InformationPackage.PORT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_PORT_REALIZATIONS = InformationPackage.PORT__OWNED_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_PORT_ALLOCATIONS = InformationPackage.PORT__OWNED_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Incoming Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__INCOMING_INFORMATION_FLOWS = InformationPackage.PORT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OUTGOING_INFORMATION_FLOWS = InformationPackage.PORT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__INFORMATION_FLOWS = InformationPackage.PORT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__IS_ABSTRACT = InformationPackage.PORT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__IS_STATIC = InformationPackage.PORT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__VISIBILITY = InformationPackage.PORT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ABSTRACT_TYPE = InformationPackage.PORT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__TYPE = InformationPackage.PORT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ORDERED = InformationPackage.PORT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__UNIQUE = InformationPackage.PORT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__MIN_INCLUSIVE = InformationPackage.PORT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__MAX_INCLUSIVE = InformationPackage.PORT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_DEFAULT_VALUE = InformationPackage.PORT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MIN_VALUE = InformationPackage.PORT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MAX_VALUE = InformationPackage.PORT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_NULL_VALUE = InformationPackage.PORT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MIN_CARD = InformationPackage.PORT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MIN_LENGTH = InformationPackage.PORT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MAX_CARD = InformationPackage.PORT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__OWNED_MAX_LENGTH = InformationPackage.PORT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__FINAL = InformationPackage.PORT_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__AGGREGATION_KIND = InformationPackage.PORT_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__IS_DERIVED = InformationPackage.PORT_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__IS_READ_ONLY = InformationPackage.PORT_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__IS_PART_OF_KEY = InformationPackage.PORT_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ASSOCIATION = InformationPackage.PORT_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Orientation</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ORIENTATION = InformationPackage.PORT_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__KIND = InformationPackage.PORT_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__COMPONENT_EXCHANGES = InformationPackage.PORT_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Allocated Function Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ALLOCATED_FUNCTION_PORTS = InformationPackage.PORT_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Delegated Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__DELEGATED_COMPONENT_PORTS = InformationPackage.PORT_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Delegating Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__DELEGATING_COMPONENT_PORTS = InformationPackage.PORT_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Allocating Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__ALLOCATING_PHYSICAL_PORTS = InformationPackage.PORT_FEATURE_COUNT + 32;

	/**
   * The feature id for the '<em><b>Realized Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__REALIZED_COMPONENT_PORTS = InformationPackage.PORT_FEATURE_COUNT + 33;

	/**
   * The feature id for the '<em><b>Realizing Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT__REALIZING_COMPONENT_PORTS = InformationPackage.PORT_FEATURE_COUNT + 34;

	/**
   * The number of structural features of the '<em>Component Port</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_FEATURE_COUNT = InformationPackage.PORT_FEATURE_COUNT + 35;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationImpl <em>Component Port Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPortAllocation()
   * @generated
   */
	int COMPONENT_PORT_ALLOCATION = 32;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Owned Component Port Allocation Ends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__OWNED_COMPONENT_PORT_ALLOCATION_ENDS = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocated Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__ALLOCATED_PORT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Allocating Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION__ALLOCATING_PORT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Component Port Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationEndImpl <em>Component Port Allocation End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationEndImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPortAllocationEnd()
   * @generated
   */
	int COMPONENT_PORT_ALLOCATION_END = 33;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__PORT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__PART = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owning Component Port Allocation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END__OWNING_COMPONENT_PORT_ALLOCATION = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Component Port Allocation End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PORT_ALLOCATION_END_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl <em>Functional Chain Involvement Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainInvolvementLink()
   * @generated
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK = 34;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OWNED_EXTENSIONS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__ID = FUNCTIONAL_CHAIN_INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SID = FUNCTIONAL_CHAIN_INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__CONSTRAINTS = FUNCTIONAL_CHAIN_INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OWNED_CONSTRAINTS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OWNED_MIGRATED_ELEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__REALIZED_FLOW = FUNCTIONAL_CHAIN_INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__INCOMING_TRACES = FUNCTIONAL_CHAIN_INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OUTGOING_TRACES = FUNCTIONAL_CHAIN_INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__VISIBLE_IN_DOC = FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__VISIBLE_IN_LM = FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SUMMARY = FUNCTIONAL_CHAIN_INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__DESCRIPTION = FUNCTIONAL_CHAIN_INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__REVIEW = FUNCTIONAL_CHAIN_INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OWNED_PROPERTY_VALUES = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__APPLIED_PROPERTY_VALUES = FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS = FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__STATUS = FUNCTIONAL_CHAIN_INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__FEATURES = FUNCTIONAL_CHAIN_INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__INVOLVER = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__INVOLVED = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Next Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Previous Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__INVOLVED_ELEMENT = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Reference Hierarchy</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE_REFERENCE_HIERARCHY = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target Reference Hierarchy</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET_REFERENCE_HIERARCHY = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Exchange Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Exchanged Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Functional Chain Involvement Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_FEATURE_COUNT = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl <em>Sequence Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getSequenceLink()
   * @generated
   */
	int SEQUENCE_LINK = 35;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Source Reference Hierarchy</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target Reference Hierarchy</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__CONDITION = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__LINKS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__SOURCE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK__TARGET = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Sequence Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.SequenceLinkEnd <em>Sequence Link End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.SequenceLinkEnd
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getSequenceLinkEnd()
   * @generated
   */
	int SEQUENCE_LINK_END = 36;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The number of structural features of the '<em>Sequence Link End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_LINK_END_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementFunctionImpl <em>Functional Chain Involvement Function</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementFunctionImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainInvolvementFunction()
   * @generated
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION = 37;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OWNED_EXTENSIONS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__ID = FUNCTIONAL_CHAIN_INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__SID = FUNCTIONAL_CHAIN_INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__CONSTRAINTS = FUNCTIONAL_CHAIN_INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OWNED_CONSTRAINTS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OWNED_MIGRATED_ELEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__REALIZED_FLOW = FUNCTIONAL_CHAIN_INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INCOMING_TRACES = FUNCTIONAL_CHAIN_INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OUTGOING_TRACES = FUNCTIONAL_CHAIN_INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__VISIBLE_IN_DOC = FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__VISIBLE_IN_LM = FUNCTIONAL_CHAIN_INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__SUMMARY = FUNCTIONAL_CHAIN_INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__DESCRIPTION = FUNCTIONAL_CHAIN_INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__REVIEW = FUNCTIONAL_CHAIN_INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OWNED_PROPERTY_VALUES = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__APPLIED_PROPERTY_VALUES = FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS = FUNCTIONAL_CHAIN_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS = FUNCTIONAL_CHAIN_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__STATUS = FUNCTIONAL_CHAIN_INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__FEATURES = FUNCTIONAL_CHAIN_INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INVOLVER = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INVOLVED = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Next Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Previous Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS = FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INVOLVED_ELEMENT = FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT;

	/**
   * The feature id for the '<em><b>Outgoing Involvement Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OUTGOING_INVOLVEMENT_LINKS = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Incoming Involvement Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INCOMING_INVOLVEMENT_LINKS = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Functional Chain Involvement Function</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_FEATURE_COUNT = FUNCTIONAL_CHAIN_INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.impl.ControlNodeImpl <em>Control Node</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.impl.ControlNodeImpl
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getControlNode()
   * @generated
   */
	int CONTROL_NODE = 38;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OWNED_EXTENSIONS = SEQUENCE_LINK_END__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__ID = SEQUENCE_LINK_END__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__SID = SEQUENCE_LINK_END__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__CONSTRAINTS = SEQUENCE_LINK_END__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OWNED_CONSTRAINTS = SEQUENCE_LINK_END__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OWNED_MIGRATED_ELEMENTS = SEQUENCE_LINK_END__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__INCOMING_TRACES = SEQUENCE_LINK_END__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OUTGOING_TRACES = SEQUENCE_LINK_END__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__VISIBLE_IN_DOC = SEQUENCE_LINK_END__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__VISIBLE_IN_LM = SEQUENCE_LINK_END__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__SUMMARY = SEQUENCE_LINK_END__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__DESCRIPTION = SEQUENCE_LINK_END__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__REVIEW = SEQUENCE_LINK_END__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OWNED_PROPERTY_VALUES = SEQUENCE_LINK_END__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OWNED_ENUMERATION_PROPERTY_TYPES = SEQUENCE_LINK_END__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__APPLIED_PROPERTY_VALUES = SEQUENCE_LINK_END__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__OWNED_PROPERTY_VALUE_GROUPS = SEQUENCE_LINK_END__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__APPLIED_PROPERTY_VALUE_GROUPS = SEQUENCE_LINK_END__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__STATUS = SEQUENCE_LINK_END__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__FEATURES = SEQUENCE_LINK_END__FEATURES;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE__KIND = SEQUENCE_LINK_END_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Control Node</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTROL_NODE_FEATURE_COUNT = SEQUENCE_LINK_END_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext <em>Reference Hierarchy Context</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.ReferenceHierarchyContext
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getReferenceHierarchyContext()
   * @generated
   */
	int REFERENCE_HIERARCHY_CONTEXT = 39;

	/**
   * The feature id for the '<em><b>Source Reference Hierarchy</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY = 0;

	/**
   * The feature id for the '<em><b>Target Reference Hierarchy</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY = 1;

	/**
   * The number of structural features of the '<em>Reference Hierarchy Context</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFERENCE_HIERARCHY_CONTEXT_FEATURE_COUNT = 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.FunctionalChainKind <em>Functional Chain Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.FunctionalChainKind
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainKind()
   * @generated
   */
	int FUNCTIONAL_CHAIN_KIND = 40;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.FunctionKind <em>Function Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.FunctionKind
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionKind()
   * @generated
   */
	int FUNCTION_KIND = 41;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.ComponentExchangeKind <em>Component Exchange Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeKind
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeKind()
   * @generated
   */
	int COMPONENT_EXCHANGE_KIND = 42;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.ComponentPortKind <em>Component Port Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.ComponentPortKind
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPortKind()
   * @generated
   */
	int COMPONENT_PORT_KIND = 43;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.OrientationPortKind <em>Orientation Port Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.OrientationPortKind
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getOrientationPortKind()
   * @generated
   */
	int ORIENTATION_PORT_KIND = 44;


	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.fa.ControlNodeKind <em>Control Node Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.fa.ControlNodeKind
   * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getControlNodeKind()
   * @generated
   */
	int CONTROL_NODE_KIND = 45;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture <em>Abstract Functional Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Functional Architecture</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture
   * @generated
   */
	EClass getAbstractFunctionalArchitecture();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionPkg <em>Owned Function Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Function Pkg</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionPkg()
   * @see #getAbstractFunctionalArchitecture()
   * @generated
   */
	EReference getAbstractFunctionalArchitecture_OwnedFunctionPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchanges()
   * @see #getAbstractFunctionalArchitecture()
   * @generated
   */
	EReference getAbstractFunctionalArchitecture_OwnedComponentExchanges();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Categories</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchangeCategories()
   * @see #getAbstractFunctionalArchitecture()
   * @generated
   */
	EReference getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionalLinks <em>Owned Functional Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Links</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionalLinks()
   * @see #getAbstractFunctionalArchitecture()
   * @generated
   */
	EReference getAbstractFunctionalArchitecture_OwnedFunctionalLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionalAllocations <em>Owned Functional Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Allocations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionalAllocations()
   * @see #getAbstractFunctionalArchitecture()
   * @generated
   */
	EReference getAbstractFunctionalArchitecture_OwnedFunctionalAllocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchangeRealizations <em>Owned Component Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchangeRealizations()
   * @see #getAbstractFunctionalArchitecture()
   * @generated
   */
	EReference getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock <em>Abstract Functional Block</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Functional Block</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock
   * @generated
   */
	EClass getAbstractFunctionalBlock();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedFunctionalAllocation <em>Owned Functional Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedFunctionalAllocation()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_OwnedFunctionalAllocation();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedComponentExchanges()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_OwnedComponentExchanges();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Categories</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOwnedComponentExchangeCategories()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_OwnedComponentExchangeCategories();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getFunctionalAllocations <em>Functional Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Functional Allocations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getFunctionalAllocations()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_FunctionalAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getAllocatedFunctions <em>Allocated Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Functions</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getAllocatedFunctions()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_AllocatedFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getInExchangeLinks <em>In Exchange Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>In Exchange Links</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getInExchangeLinks()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_InExchangeLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOutExchangeLinks <em>Out Exchange Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Out Exchange Links</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getOutExchangeLinks()
   * @see #getAbstractFunctionalBlock()
   * @generated
   */
	EReference getAbstractFunctionalBlock_OutExchangeLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionPkg <em>Function Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Pkg</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPkg
   * @generated
   */
	EClass getFunctionPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedFunctionalLinks <em>Owned Functional Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Links</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedFunctionalLinks()
   * @see #getFunctionPkg()
   * @generated
   */
	EReference getFunctionPkg_OwnedFunctionalLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedExchanges <em>Owned Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedExchanges()
   * @see #getFunctionPkg()
   * @generated
   */
	EReference getFunctionPkg_OwnedExchanges();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedExchangeSpecificationRealizations <em>Owned Exchange Specification Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchange Specification Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedExchangeSpecificationRealizations()
   * @see #getFunctionPkg()
   * @generated
   */
	EReference getFunctionPkg_OwnedExchangeSpecificationRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedCategories <em>Owned Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Categories</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedCategories()
   * @see #getFunctionPkg()
   * @generated
   */
	EReference getFunctionPkg_OwnedCategories();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedFunctionSpecifications <em>Owned Function Specifications</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Function Specifications</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedFunctionSpecifications()
   * @see #getFunctionPkg()
   * @generated
   */
	EReference getFunctionPkg_OwnedFunctionSpecifications();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionSpecification <em>Function Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Specification</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionSpecification
   * @generated
   */
	EClass getFunctionSpecification();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getInExchangeLinks <em>In Exchange Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>In Exchange Links</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionSpecification#getInExchangeLinks()
   * @see #getFunctionSpecification()
   * @generated
   */
	EReference getFunctionSpecification_InExchangeLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getOutExchangeLinks <em>Out Exchange Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Out Exchange Links</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionSpecification#getOutExchangeLinks()
   * @see #getFunctionSpecification()
   * @generated
   */
	EReference getFunctionSpecification_OutExchangeLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getOwnedFunctionPorts <em>Owned Function Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Function Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionSpecification#getOwnedFunctionPorts()
   * @see #getFunctionSpecification()
   * @generated
   */
	EReference getFunctionSpecification_OwnedFunctionPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getSubFunctionSpecifications <em>Sub Function Specifications</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Function Specifications</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionSpecification#getSubFunctionSpecifications()
   * @see #getFunctionSpecification()
   * @generated
   */
	EReference getFunctionSpecification_SubFunctionSpecifications();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ExchangeCategory <em>Exchange Category</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Category</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeCategory
   * @generated
   */
	EClass getExchangeCategory();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeCategory#getExchanges <em>Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeCategory#getExchanges()
   * @see #getExchangeCategory()
   * @generated
   */
	EReference getExchangeCategory_Exchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ExchangeLink <em>Exchange Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Link</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeLink
   * @generated
   */
	EClass getExchangeLink();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeLink#getExchanges <em>Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeLink#getExchanges()
   * @see #getExchangeLink()
   * @generated
   */
	EReference getExchangeLink_Exchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeLink#getExchangeContainmentLinks <em>Exchange Containment Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchange Containment Links</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeLink#getExchangeContainmentLinks()
   * @see #getExchangeLink()
   * @generated
   */
	EReference getExchangeLink_ExchangeContainmentLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.ExchangeLink#getOwnedExchangeContainments <em>Owned Exchange Containments</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchange Containments</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeLink#getOwnedExchangeContainments()
   * @see #getExchangeLink()
   * @generated
   */
	EReference getExchangeLink_OwnedExchangeContainments();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeLink#getSources <em>Sources</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sources</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeLink#getSources()
   * @see #getExchangeLink()
   * @generated
   */
	EReference getExchangeLink_Sources();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeLink#getDestinations <em>Destinations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Destinations</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeLink#getDestinations()
   * @see #getExchangeLink()
   * @generated
   */
	EReference getExchangeLink_Destinations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ExchangeContainment <em>Exchange Containment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Containment</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeContainment
   * @generated
   */
	EClass getExchangeContainment();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getExchange <em>Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeContainment#getExchange()
   * @see #getExchangeContainment()
   * @generated
   */
	EReference getExchangeContainment_Exchange();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Link</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeContainment#getLink()
   * @see #getExchangeContainment()
   * @generated
   */
	EReference getExchangeContainment_Link();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification <em>Exchange Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Specification</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification
   * @generated
   */
	EClass getExchangeSpecification();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getContainingLink <em>Containing Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Containing Link</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getContainingLink()
   * @see #getExchangeSpecification()
   * @generated
   */
	EReference getExchangeSpecification_ContainingLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Link</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getLink()
   * @see #getExchangeSpecification()
   * @generated
   */
	EReference getExchangeSpecification_Link();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getOutgoingExchangeSpecificationRealizations <em>Outgoing Exchange Specification Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Exchange Specification Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getOutgoingExchangeSpecificationRealizations()
   * @see #getExchangeSpecification()
   * @generated
   */
	EReference getExchangeSpecification_OutgoingExchangeSpecificationRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getIncomingExchangeSpecificationRealizations <em>Incoming Exchange Specification Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Exchange Specification Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getIncomingExchangeSpecificationRealizations()
   * @see #getExchangeSpecification()
   * @generated
   */
	EReference getExchangeSpecification_IncomingExchangeSpecificationRealizations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification <em>Functional Exchange Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Exchange Specification</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification
   * @generated
   */
	EClass getFunctionalExchangeSpecification();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification#getFunctionalExchanges <em>Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification#getFunctionalExchanges()
   * @see #getFunctionalExchangeSpecification()
   * @generated
   */
	EReference getFunctionalExchangeSpecification_FunctionalExchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalChain <em>Functional Chain</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain
   * @generated
   */
	EClass getFunctionalChain();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getKind()
   * @see #getFunctionalChain()
   * @generated
   */
	EAttribute getFunctionalChain_Kind();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedFunctionalChainInvolvements <em>Owned Functional Chain Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Chain Involvements</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedFunctionalChainInvolvements()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_OwnedFunctionalChainInvolvements();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedFunctionalChainRealizations <em>Owned Functional Chain Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Chain Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedFunctionalChainRealizations()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_OwnedFunctionalChainRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctionalChainInvolvements <em>Involved Functional Chain Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Functional Chain Involvements</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctionalChainInvolvements()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_InvolvedFunctionalChainInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctions <em>Involved Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Functions</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctions()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_InvolvedFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctionalExchanges <em>Involved Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedFunctionalExchanges()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_InvolvedFunctionalExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedElements <em>Involved Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Elements</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvedElements()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_InvolvedElements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getEnactedFunctions <em>Enacted Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Enacted Functions</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getEnactedFunctions()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_EnactedFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getEnactedFunctionalBlocks <em>Enacted Functional Blocks</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Enacted Functional Blocks</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getEnactedFunctionalBlocks()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_EnactedFunctionalBlocks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getAvailableInStates <em>Available In States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Available In States</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getAvailableInStates()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_AvailableInStates();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getFirstFunctionalChainInvolvements <em>First Functional Chain Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>First Functional Chain Involvements</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getFirstFunctionalChainInvolvements()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_FirstFunctionalChainInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvingCapabilities <em>Involving Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Capabilities</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvingCapabilities()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_InvolvingCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getInvolvingCapabilityRealizations()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_InvolvingCapabilityRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getRealizedFunctionalChains <em>Realized Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getRealizedFunctionalChains()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_RealizedFunctionalChains();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getRealizingFunctionalChains <em>Realizing Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getRealizingFunctionalChains()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_RealizingFunctionalChains();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Pre Condition</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getPreCondition()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_PreCondition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Post Condition</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getPostCondition()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_PostCondition();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedSequenceNodes <em>Owned Sequence Nodes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Sequence Nodes</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedSequenceNodes()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_OwnedSequenceNodes();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedSequenceLinks <em>Owned Sequence Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Sequence Links</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChain#getOwnedSequenceLinks()
   * @see #getFunctionalChain()
   * @generated
   */
	EReference getFunctionalChain_OwnedSequenceLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer <em>Abstract Functional Chain Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Functional Chain Container</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer
   * @generated
   */
	EClass getAbstractFunctionalChainContainer();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer#getOwnedFunctionalChains <em>Owned Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer#getOwnedFunctionalChains()
   * @see #getAbstractFunctionalChainContainer()
   * @generated
   */
	EReference getAbstractFunctionalChainContainer_OwnedFunctionalChains();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement <em>Functional Chain Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain Involvement</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvement
   * @generated
   */
	EClass getFunctionalChainInvolvement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getNextFunctionalChainInvolvements <em>Next Functional Chain Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Next Functional Chain Involvements</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getNextFunctionalChainInvolvements()
   * @see #getFunctionalChainInvolvement()
   * @generated
   */
	EReference getFunctionalChainInvolvement_NextFunctionalChainInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getPreviousFunctionalChainInvolvements <em>Previous Functional Chain Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Previous Functional Chain Involvements</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getPreviousFunctionalChainInvolvements()
   * @see #getFunctionalChainInvolvement()
   * @generated
   */
	EReference getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getInvolvedElement <em>Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Involved Element</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getInvolvedElement()
   * @see #getFunctionalChainInvolvement()
   * @generated
   */
	EReference getFunctionalChainInvolvement_InvolvedElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalChainReference <em>Functional Chain Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain Reference</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainReference
   * @generated
   */
	EClass getFunctionalChainReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChainReference#getReferencedFunctionalChain <em>Referenced Functional Chain</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Functional Chain</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainReference#getReferencedFunctionalChain()
   * @see #getFunctionalChainReference()
   * @generated
   */
	EReference getFunctionalChainReference_ReferencedFunctionalChain();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionInputPort <em>Function Input Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Input Port</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionInputPort
   * @generated
   */
	EClass getFunctionInputPort();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionInputPort#getIncomingExchangeItems <em>Incoming Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionInputPort#getIncomingExchangeItems()
   * @see #getFunctionInputPort()
   * @generated
   */
	EReference getFunctionInputPort_IncomingExchangeItems();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionInputPort#getIncomingFunctionalExchanges <em>Incoming Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionInputPort#getIncomingFunctionalExchanges()
   * @see #getFunctionInputPort()
   * @generated
   */
	EReference getFunctionInputPort_IncomingFunctionalExchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionOutputPort <em>Function Output Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Output Port</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionOutputPort
   * @generated
   */
	EClass getFunctionOutputPort();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionOutputPort#getOutgoingExchangeItems <em>Outgoing Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionOutputPort#getOutgoingExchangeItems()
   * @see #getFunctionOutputPort()
   * @generated
   */
	EReference getFunctionOutputPort_OutgoingExchangeItems();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionOutputPort#getOutgoingFunctionalExchanges <em>Outgoing Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionOutputPort#getOutgoingFunctionalExchanges()
   * @see #getFunctionOutputPort()
   * @generated
   */
	EReference getFunctionOutputPort_OutgoingFunctionalExchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionAllocation <em>Abstract Function Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Function Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionAllocation
   * @generated
   */
	EClass getAbstractFunctionAllocation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation <em>Component Functional Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Functional Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation
   * @generated
   */
	EClass getComponentFunctionalAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getFunction <em>Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Function</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getFunction()
   * @see #getComponentFunctionalAllocation()
   * @generated
   */
	EReference getComponentFunctionalAllocation_Function();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Block</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getBlock()
   * @see #getComponentFunctionalAllocation()
   * @generated
   */
	EReference getComponentFunctionalAllocation_Block();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalChainRealization <em>Functional Chain Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain Realization</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainRealization
   * @generated
   */
	EClass getFunctionalChainRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization <em>Exchange Specification Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Specification Realization</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization
   * @generated
   */
	EClass getExchangeSpecificationRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizedExchangeSpecification <em>Realized Exchange Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Exchange Specification</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizedExchangeSpecification()
   * @see #getExchangeSpecificationRealization()
   * @generated
   */
	EReference getExchangeSpecificationRealization_RealizedExchangeSpecification();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizingExchangeSpecification <em>Realizing Exchange Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Exchange Specification</em>'.
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizingExchangeSpecification()
   * @see #getExchangeSpecificationRealization()
   * @generated
   */
	EReference getExchangeSpecificationRealization_RealizingExchangeSpecification();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalExchangeRealization <em>Functional Exchange Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Exchange Realization</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchangeRealization
   * @generated
   */
	EClass getFunctionalExchangeRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalExchangeRealization#getRealizedFunctionalExchange <em>Realized Functional Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Functional Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchangeRealization#getRealizedFunctionalExchange()
   * @see #getFunctionalExchangeRealization()
   * @generated
   */
	EReference getFunctionalExchangeRealization_RealizedFunctionalExchange();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalExchangeRealization#getRealizingFunctionalExchange <em>Realizing Functional Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Functional Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchangeRealization#getRealizingFunctionalExchange()
   * @see #getFunctionalExchangeRealization()
   * @generated
   */
	EReference getFunctionalExchangeRealization_RealizingFunctionalExchange();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionRealization <em>Function Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Realization</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionRealization
   * @generated
   */
	EClass getFunctionRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatedFunction <em>Allocated Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Function</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatedFunction()
   * @see #getFunctionRealization()
   * @generated
   */
	EReference getFunctionRealization_AllocatedFunction();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatingFunction <em>Allocating Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Function</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionRealization#getAllocatingFunction()
   * @see #getFunctionRealization()
   * @generated
   */
	EReference getFunctionRealization_AllocatingFunction();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalExchange <em>Functional Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange
   * @generated
   */
	EClass getFunctionalExchange();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getExchangeSpecifications <em>Exchange Specifications</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchange Specifications</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getExchangeSpecifications()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_ExchangeSpecifications();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getInvolvingFunctionalChains <em>Involving Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getInvolvingFunctionalChains()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_InvolvingFunctionalChains();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getExchangedItems <em>Exchanged Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchanged Items</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getExchangedItems()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_ExchangedItems();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getAllocatingComponentExchanges <em>Allocating Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getAllocatingComponentExchanges()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_AllocatingComponentExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingComponentExchangeFunctionalExchangeRealizations <em>Incoming Component Exchange Functional Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Component Exchange Functional Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingComponentExchangeFunctionalExchangeRealizations()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingFunctionalExchangeRealizations <em>Incoming Functional Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Functional Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingFunctionalExchangeRealizations()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_IncomingFunctionalExchangeRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getOutgoingFunctionalExchangeRealizations <em>Outgoing Functional Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Functional Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getOutgoingFunctionalExchangeRealizations()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_OutgoingFunctionalExchangeRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getCategories <em>Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Categories</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getCategories()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_Categories();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getOwnedFunctionalExchangeRealizations <em>Owned Functional Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getOwnedFunctionalExchangeRealizations()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_OwnedFunctionalExchangeRealizations();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getSourceFunctionOutputPort <em>Source Function Output Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Function Output Port</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getSourceFunctionOutputPort()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_SourceFunctionOutputPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getTargetFunctionInputPort <em>Target Function Input Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Function Input Port</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getTargetFunctionInputPort()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_TargetFunctionInputPort();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getRealizedFunctionalExchanges <em>Realized Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getRealizedFunctionalExchanges()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_RealizedFunctionalExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getRealizingFunctionalExchanges <em>Realizing Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getRealizingFunctionalExchanges()
   * @see #getFunctionalExchange()
   * @generated
   */
	EReference getFunctionalExchange_RealizingFunctionalExchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.AbstractFunction <em>Abstract Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Function</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction
   * @generated
   */
	EClass getAbstractFunction();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getKind()
   * @see #getAbstractFunction()
   * @generated
   */
	EAttribute getAbstractFunction_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getCondition()
   * @see #getAbstractFunction()
   * @generated
   */
	EAttribute getAbstractFunction_Condition();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctions <em>Owned Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functions</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctions()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_OwnedFunctions();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctionRealizations <em>Owned Function Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Function Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctionRealizations()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_OwnedFunctionRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctionalExchanges <em>Owned Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getOwnedFunctionalExchanges()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_OwnedFunctionalExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getSubFunctions <em>Sub Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Functions</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getSubFunctions()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_SubFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getOutFunctionRealizations <em>Out Function Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Out Function Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getOutFunctionRealizations()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_OutFunctionRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInFunctionRealizations <em>In Function Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>In Function Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getInFunctionRealizations()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_InFunctionRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getComponentFunctionalAllocations <em>Component Functional Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Component Functional Allocations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getComponentFunctionalAllocations()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_ComponentFunctionalAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getAllocationBlocks <em>Allocation Blocks</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocation Blocks</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getAllocationBlocks()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_AllocationBlocks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getAvailableInStates <em>Available In States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Available In States</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getAvailableInStates()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_AvailableInStates();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingCapabilities <em>Involving Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Capabilities</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingCapabilities()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_InvolvingCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingCapabilityRealizations()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_InvolvingCapabilityRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingFunctionalChains <em>Involving Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getInvolvingFunctionalChains()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_InvolvingFunctionalChains();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getLinkedStateMachine <em>Linked State Machine</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Linked State Machine</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getLinkedStateMachine()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_LinkedStateMachine();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getLinkedFunctionSpecification <em>Linked Function Specification</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Linked Function Specification</em>'.
   * @see org.polarsys.capella.core.data.fa.AbstractFunction#getLinkedFunctionSpecification()
   * @see #getAbstractFunction()
   * @generated
   */
	EReference getAbstractFunction_LinkedFunctionSpecification();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionPort <em>Function Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Port</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPort
   * @generated
   */
	EClass getFunctionPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort <em>Represented Component Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Represented Component Port</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort()
   * @see #getFunctionPort()
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   */
	@Deprecated
	EReference getFunctionPort_RepresentedComponentPort();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionPort#getAllocatorComponentPorts <em>Allocator Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocator Component Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPort#getAllocatorComponentPorts()
   * @see #getFunctionPort()
   * @generated
   */
	EReference getFunctionPort_AllocatorComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionPort#getRealizedFunctionPorts <em>Realized Function Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Function Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPort#getRealizedFunctionPorts()
   * @see #getFunctionPort()
   * @generated
   */
	EReference getFunctionPort_RealizedFunctionPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionPort#getRealizingFunctionPorts <em>Realizing Function Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Function Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionPort#getRealizingFunctionPorts()
   * @see #getFunctionPort()
   * @generated
   */
	EReference getFunctionPort_RealizingFunctionPorts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchange <em>Component Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange
   * @generated
   */
	EClass getComponentExchange();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getKind()
   * @see #getComponentExchange()
   * @generated
   */
	EAttribute getComponentExchange_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.ComponentExchange#isOriented <em>Oriented</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Oriented</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#isOriented()
   * @see #getComponentExchange()
   * @generated
   */
	EAttribute getComponentExchange_Oriented();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getAllocatedFunctionalExchanges <em>Allocated Functional Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Functional Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getAllocatedFunctionalExchanges()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_AllocatedFunctionalExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getIncomingComponentExchangeRealizations <em>Incoming Component Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Component Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getIncomingComponentExchangeRealizations()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_IncomingComponentExchangeRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeRealizations <em>Outgoing Component Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Component Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeRealizations()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_OutgoingComponentExchangeRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeFunctionalExchangeAllocations <em>Outgoing Component Exchange Functional Exchange Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Component Exchange Functional Exchange Allocations</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getOutgoingComponentExchangeFunctionalExchangeAllocations()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeFunctionalExchangeAllocations <em>Owned Component Exchange Functional Exchange Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Functional Exchange Allocations</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeFunctionalExchangeAllocations()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeRealizations <em>Owned Component Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeRealizations()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_OwnedComponentExchangeRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeEnds <em>Owned Component Exchange Ends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Ends</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getOwnedComponentExchangeEnds()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_OwnedComponentExchangeEnds();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getSourcePort <em>Source Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getSourcePort()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_SourcePort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getSourcePart <em>Source Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Part</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getSourcePart()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_SourcePart();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getTargetPort <em>Target Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getTargetPort()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_TargetPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getTargetPart <em>Target Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Part</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getTargetPart()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_TargetPart();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getCategories <em>Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Categories</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getCategories()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_Categories();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getAllocatorPhysicalLinks <em>Allocator Physical Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocator Physical Links</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getAllocatorPhysicalLinks()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_AllocatorPhysicalLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getRealizedComponentExchanges <em>Realized Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getRealizedComponentExchanges()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_RealizedComponentExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchange#getRealizingComponentExchanges <em>Realizing Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchange#getRealizingComponentExchanges()
   * @see #getComponentExchange()
   * @generated
   */
	EReference getComponentExchange_RealizingComponentExchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocation <em>Component Exchange Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocation
   * @generated
   */
	EClass getComponentExchangeAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocation#getComponentExchangeAllocated <em>Component Exchange Allocated</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Component Exchange Allocated</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocation#getComponentExchangeAllocated()
   * @see #getComponentExchangeAllocation()
   * @generated
   */
	EReference getComponentExchangeAllocation_ComponentExchangeAllocated();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocation#getComponentExchangeAllocator <em>Component Exchange Allocator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Component Exchange Allocator</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocation#getComponentExchangeAllocator()
   * @see #getComponentExchangeAllocation()
   * @generated
   */
	EReference getComponentExchangeAllocation_ComponentExchangeAllocator();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocator <em>Component Exchange Allocator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange Allocator</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocator
   * @generated
   */
	EClass getComponentExchangeAllocator();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocator#getOwnedComponentExchangeAllocations <em>Owned Component Exchange Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Allocations</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocator#getOwnedComponentExchangeAllocations()
   * @see #getComponentExchangeAllocator()
   * @generated
   */
	EReference getComponentExchangeAllocator_OwnedComponentExchangeAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocator#getAllocatedComponentExchanges <em>Allocated Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocator#getAllocatedComponentExchanges()
   * @see #getComponentExchangeAllocator()
   * @generated
   */
	EReference getComponentExchangeAllocator_AllocatedComponentExchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeCategory <em>Component Exchange Category</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange Category</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeCategory
   * @generated
   */
	EClass getComponentExchangeCategory();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentExchangeCategory#getExchanges <em>Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeCategory#getExchanges()
   * @see #getComponentExchangeCategory()
   * @generated
   */
	EReference getComponentExchangeCategory_Exchanges();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd <em>Component Exchange End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange End</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeEnd
   * @generated
   */
	EClass getComponentExchangeEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPort()
   * @see #getComponentExchangeEnd()
   * @generated
   */
	EReference getComponentExchangeEnd_Port();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPart <em>Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Part</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeEnd#getPart()
   * @see #getComponentExchangeEnd()
   * @generated
   */
	EReference getComponentExchangeEnd_Part();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation <em>Component Exchange Functional Exchange Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange Functional Exchange Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation
   * @generated
   */
	EClass getComponentExchangeFunctionalExchangeAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatedFunctionalExchange <em>Allocated Functional Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Functional Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatedFunctionalExchange()
   * @see #getComponentExchangeFunctionalExchangeAllocation()
   * @generated
   */
	EReference getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatingComponentExchange <em>Allocating Component Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Component Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation#getAllocatingComponentExchange()
   * @see #getComponentExchangeFunctionalExchangeAllocation()
   * @generated
   */
	EReference getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization <em>Component Exchange Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Exchange Realization</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeRealization
   * @generated
   */
	EClass getComponentExchangeRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatedComponentExchange <em>Allocated Component Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Component Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatedComponentExchange()
   * @see #getComponentExchangeRealization()
   * @generated
   */
	EReference getComponentExchangeRealization_AllocatedComponentExchange();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatingComponentExchange <em>Allocating Component Exchange</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Component Exchange</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeRealization#getAllocatingComponentExchange()
   * @see #getComponentExchangeRealization()
   * @generated
   */
	EReference getComponentExchangeRealization_AllocatingComponentExchange();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentPort <em>Component Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort
   * @generated
   */
	EClass getComponentPort();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.ComponentPort#getOrientation <em>Orientation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Orientation</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getOrientation()
   * @see #getComponentPort()
   * @generated
   */
	EAttribute getComponentPort_Orientation();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.ComponentPort#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getKind()
   * @see #getComponentPort()
   * @generated
   */
	EAttribute getComponentPort_Kind();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getComponentExchanges <em>Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getComponentExchanges()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_ComponentExchanges();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getAllocatedFunctionPorts <em>Allocated Function Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Function Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getAllocatedFunctionPorts()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_AllocatedFunctionPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getDelegatedComponentPorts <em>Delegated Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Delegated Component Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getDelegatedComponentPorts()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_DelegatedComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getDelegatingComponentPorts <em>Delegating Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Delegating Component Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getDelegatingComponentPorts()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_DelegatingComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getAllocatingPhysicalPorts <em>Allocating Physical Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Physical Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getAllocatingPhysicalPorts()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_AllocatingPhysicalPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getRealizedComponentPorts <em>Realized Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Component Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getRealizedComponentPorts()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_RealizedComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ComponentPort#getRealizingComponentPorts <em>Realizing Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Component Ports</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getRealizingComponentPorts()
   * @see #getComponentPort()
   * @generated
   */
	EReference getComponentPort_RealizingComponentPorts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocation <em>Component Port Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Port Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocation
   * @generated
   */
	EClass getComponentPortAllocation();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocation#getOwnedComponentPortAllocationEnds <em>Owned Component Port Allocation Ends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Port Allocation Ends</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocation#getOwnedComponentPortAllocationEnds()
   * @see #getComponentPortAllocation()
   * @generated
   */
	EReference getComponentPortAllocation_OwnedComponentPortAllocationEnds();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocation#getAllocatedPort <em>Allocated Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocation#getAllocatedPort()
   * @see #getComponentPortAllocation()
   * @generated
   */
	EReference getComponentPortAllocation_AllocatedPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocation#getAllocatingPort <em>Allocating Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocation#getAllocatingPort()
   * @see #getComponentPortAllocation()
   * @generated
   */
	EReference getComponentPortAllocation_AllocatingPort();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd <em>Component Port Allocation End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Port Allocation End</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd
   * @generated
   */
	EClass getComponentPortAllocationEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Port</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPort()
   * @see #getComponentPortAllocationEnd()
   * @generated
   */
	EReference getComponentPortAllocationEnd_Port();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPart <em>Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Part</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPart()
   * @see #getComponentPortAllocationEnd()
   * @generated
   */
	EReference getComponentPortAllocationEnd_Part();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getOwningComponentPortAllocation <em>Owning Component Port Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Owning Component Port Allocation</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getOwningComponentPortAllocation()
   * @see #getComponentPortAllocationEnd()
   * @generated
   */
	EReference getComponentPortAllocationEnd_OwningComponentPortAllocation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink <em>Functional Chain Involvement Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain Involvement Link</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink
   * @generated
   */
	EClass getFunctionalChainInvolvementLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangeContext <em>Exchange Context</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Exchange Context</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangeContext()
   * @see #getFunctionalChainInvolvementLink()
   * @generated
   */
	EReference getFunctionalChainInvolvementLink_ExchangeContext();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangedItems <em>Exchanged Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchanged Items</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangedItems()
   * @see #getFunctionalChainInvolvementLink()
   * @generated
   */
	EReference getFunctionalChainInvolvementLink_ExchangedItems();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getSource()
   * @see #getFunctionalChainInvolvementLink()
   * @generated
   */
	EReference getFunctionalChainInvolvementLink_Source();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getTarget()
   * @see #getFunctionalChainInvolvementLink()
   * @generated
   */
	EReference getFunctionalChainInvolvementLink_Target();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.SequenceLink <em>Sequence Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sequence Link</em>'.
   * @see org.polarsys.capella.core.data.fa.SequenceLink
   * @generated
   */
	EClass getSequenceLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.SequenceLink#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Condition</em>'.
   * @see org.polarsys.capella.core.data.fa.SequenceLink#getCondition()
   * @see #getSequenceLink()
   * @generated
   */
	EReference getSequenceLink_Condition();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.SequenceLink#getLinks <em>Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Links</em>'.
   * @see org.polarsys.capella.core.data.fa.SequenceLink#getLinks()
   * @see #getSequenceLink()
   * @generated
   */
	EReference getSequenceLink_Links();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.SequenceLink#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.core.data.fa.SequenceLink#getSource()
   * @see #getSequenceLink()
   * @generated
   */
	EReference getSequenceLink_Source();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.fa.SequenceLink#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.core.data.fa.SequenceLink#getTarget()
   * @see #getSequenceLink()
   * @generated
   */
	EReference getSequenceLink_Target();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.SequenceLinkEnd <em>Sequence Link End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sequence Link End</em>'.
   * @see org.polarsys.capella.core.data.fa.SequenceLinkEnd
   * @generated
   */
	EClass getSequenceLinkEnd();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction <em>Functional Chain Involvement Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain Involvement Function</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction
   * @generated
   */
	EClass getFunctionalChainInvolvementFunction();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction#getOutgoingInvolvementLinks <em>Outgoing Involvement Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Involvement Links</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction#getOutgoingInvolvementLinks()
   * @see #getFunctionalChainInvolvementFunction()
   * @generated
   */
	EReference getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction#getIncomingInvolvementLinks <em>Incoming Involvement Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Involvement Links</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction#getIncomingInvolvementLinks()
   * @see #getFunctionalChainInvolvementFunction()
   * @generated
   */
	EReference getFunctionalChainInvolvementFunction_IncomingInvolvementLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ControlNode <em>Control Node</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Control Node</em>'.
   * @see org.polarsys.capella.core.data.fa.ControlNode
   * @generated
   */
	EClass getControlNode();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.fa.ControlNode#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.ControlNode#getKind()
   * @see #getControlNode()
   * @generated
   */
	EAttribute getControlNode_Kind();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext <em>Reference Hierarchy Context</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference Hierarchy Context</em>'.
   * @see org.polarsys.capella.core.data.fa.ReferenceHierarchyContext
   * @generated
   */
	EClass getReferenceHierarchyContext();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext#getSourceReferenceHierarchy <em>Source Reference Hierarchy</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Source Reference Hierarchy</em>'.
   * @see org.polarsys.capella.core.data.fa.ReferenceHierarchyContext#getSourceReferenceHierarchy()
   * @see #getReferenceHierarchyContext()
   * @generated
   */
	EReference getReferenceHierarchyContext_SourceReferenceHierarchy();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext#getTargetReferenceHierarchy <em>Target Reference Hierarchy</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Target Reference Hierarchy</em>'.
   * @see org.polarsys.capella.core.data.fa.ReferenceHierarchyContext#getTargetReferenceHierarchy()
   * @see #getReferenceHierarchyContext()
   * @generated
   */
	EReference getReferenceHierarchyContext_TargetReferenceHierarchy();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.fa.FunctionalChainKind <em>Functional Chain Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Functional Chain Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionalChainKind
   * @generated
   */
	EEnum getFunctionalChainKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.fa.FunctionKind <em>Function Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Function Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.FunctionKind
   * @generated
   */
	EEnum getFunctionKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.fa.ComponentExchangeKind <em>Component Exchange Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Component Exchange Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeKind
   * @generated
   */
	EEnum getComponentExchangeKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.fa.ComponentPortKind <em>Component Port Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Component Port Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.ComponentPortKind
   * @generated
   */
	EEnum getComponentPortKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.fa.OrientationPortKind <em>Orientation Port Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Orientation Port Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.OrientationPortKind
   * @generated
   */
	EEnum getOrientationPortKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.fa.ControlNodeKind <em>Control Node Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Control Node Kind</em>'.
   * @see org.polarsys.capella.core.data.fa.ControlNodeKind
   * @generated
   */
	EEnum getControlNodeKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	FaFactory getFaFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalArchitectureImpl <em>Abstract Functional Architecture</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionalArchitectureImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionalArchitecture()
     * @generated
     */
		EClass ABSTRACT_FUNCTIONAL_ARCHITECTURE = eINSTANCE.getAbstractFunctionalArchitecture();

		/**
     * The meta object literal for the '<em><b>Owned Function Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG = eINSTANCE.getAbstractFunctionalArchitecture_OwnedFunctionPkg();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchanges</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = eINSTANCE.getAbstractFunctionalArchitecture_OwnedComponentExchanges();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = eINSTANCE.getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories();

		/**
     * The meta object literal for the '<em><b>Owned Functional Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = eINSTANCE.getAbstractFunctionalArchitecture_OwnedFunctionalLinks();

		/**
     * The meta object literal for the '<em><b>Owned Functional Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = eINSTANCE.getAbstractFunctionalArchitecture_OwnedFunctionalAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = eINSTANCE.getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl <em>Abstract Functional Block</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionalBlock()
     * @generated
     */
		EClass ABSTRACT_FUNCTIONAL_BLOCK = eINSTANCE.getAbstractFunctionalBlock();

		/**
     * The meta object literal for the '<em><b>Owned Functional Allocation</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION = eINSTANCE.getAbstractFunctionalBlock_OwnedFunctionalAllocation();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchanges</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES = eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchanges();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES = eINSTANCE.getAbstractFunctionalBlock_OwnedComponentExchangeCategories();

		/**
     * The meta object literal for the '<em><b>Functional Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS = eINSTANCE.getAbstractFunctionalBlock_FunctionalAllocations();

		/**
     * The meta object literal for the '<em><b>Allocated Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS = eINSTANCE.getAbstractFunctionalBlock_AllocatedFunctions();

		/**
     * The meta object literal for the '<em><b>In Exchange Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS = eINSTANCE.getAbstractFunctionalBlock_InExchangeLinks();

		/**
     * The meta object literal for the '<em><b>Out Exchange Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS = eINSTANCE.getAbstractFunctionalBlock_OutExchangeLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionPkgImpl <em>Function Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionPkgImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionPkg()
     * @generated
     */
		EClass FUNCTION_PKG = eINSTANCE.getFunctionPkg();

		/**
     * The meta object literal for the '<em><b>Owned Functional Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS = eINSTANCE.getFunctionPkg_OwnedFunctionalLinks();

		/**
     * The meta object literal for the '<em><b>Owned Exchanges</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PKG__OWNED_EXCHANGES = eINSTANCE.getFunctionPkg_OwnedExchanges();

		/**
     * The meta object literal for the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS = eINSTANCE.getFunctionPkg_OwnedExchangeSpecificationRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PKG__OWNED_CATEGORIES = eINSTANCE.getFunctionPkg_OwnedCategories();

		/**
     * The meta object literal for the '<em><b>Owned Function Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS = eINSTANCE.getFunctionPkg_OwnedFunctionSpecifications();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl <em>Function Specification</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionSpecification()
     * @generated
     */
		EClass FUNCTION_SPECIFICATION = eINSTANCE.getFunctionSpecification();

		/**
     * The meta object literal for the '<em><b>In Exchange Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS = eINSTANCE.getFunctionSpecification_InExchangeLinks();

		/**
     * The meta object literal for the '<em><b>Out Exchange Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS = eINSTANCE.getFunctionSpecification_OutExchangeLinks();

		/**
     * The meta object literal for the '<em><b>Owned Function Ports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS = eINSTANCE.getFunctionSpecification_OwnedFunctionPorts();

		/**
     * The meta object literal for the '<em><b>Sub Function Specifications</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS = eINSTANCE.getFunctionSpecification_SubFunctionSpecifications();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeCategoryImpl <em>Exchange Category</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ExchangeCategoryImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeCategory()
     * @generated
     */
		EClass EXCHANGE_CATEGORY = eINSTANCE.getExchangeCategory();

		/**
     * The meta object literal for the '<em><b>Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_CATEGORY__EXCHANGES = eINSTANCE.getExchangeCategory_Exchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeLinkImpl <em>Exchange Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ExchangeLinkImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeLink()
     * @generated
     */
		EClass EXCHANGE_LINK = eINSTANCE.getExchangeLink();

		/**
     * The meta object literal for the '<em><b>Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_LINK__EXCHANGES = eINSTANCE.getExchangeLink_Exchanges();

		/**
     * The meta object literal for the '<em><b>Exchange Containment Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_LINK__EXCHANGE_CONTAINMENT_LINKS = eINSTANCE.getExchangeLink_ExchangeContainmentLinks();

		/**
     * The meta object literal for the '<em><b>Owned Exchange Containments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_LINK__OWNED_EXCHANGE_CONTAINMENTS = eINSTANCE.getExchangeLink_OwnedExchangeContainments();

		/**
     * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_LINK__SOURCES = eINSTANCE.getExchangeLink_Sources();

		/**
     * The meta object literal for the '<em><b>Destinations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_LINK__DESTINATIONS = eINSTANCE.getExchangeLink_Destinations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeContainmentImpl <em>Exchange Containment</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ExchangeContainmentImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeContainment()
     * @generated
     */
		EClass EXCHANGE_CONTAINMENT = eINSTANCE.getExchangeContainment();

		/**
     * The meta object literal for the '<em><b>Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_CONTAINMENT__EXCHANGE = eINSTANCE.getExchangeContainment_Exchange();

		/**
     * The meta object literal for the '<em><b>Link</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_CONTAINMENT__LINK = eINSTANCE.getExchangeContainment_Link();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationImpl <em>Exchange Specification</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeSpecification()
     * @generated
     */
		EClass EXCHANGE_SPECIFICATION = eINSTANCE.getExchangeSpecification();

		/**
     * The meta object literal for the '<em><b>Containing Link</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_SPECIFICATION__CONTAINING_LINK = eINSTANCE.getExchangeSpecification_ContainingLink();

		/**
     * The meta object literal for the '<em><b>Link</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_SPECIFICATION__LINK = eINSTANCE.getExchangeSpecification_Link();

		/**
     * The meta object literal for the '<em><b>Outgoing Exchange Specification Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS = eINSTANCE.getExchangeSpecification_OutgoingExchangeSpecificationRealizations();

		/**
     * The meta object literal for the '<em><b>Incoming Exchange Specification Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS = eINSTANCE.getExchangeSpecification_IncomingExchangeSpecificationRealizations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeSpecificationImpl <em>Functional Exchange Specification</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalExchangeSpecificationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalExchangeSpecification()
     * @generated
     */
		EClass FUNCTIONAL_EXCHANGE_SPECIFICATION = eINSTANCE.getFunctionalExchangeSpecification();

		/**
     * The meta object literal for the '<em><b>Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE_SPECIFICATION__FUNCTIONAL_EXCHANGES = eINSTANCE.getFunctionalExchangeSpecification_FunctionalExchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl <em>Functional Chain</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChain()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN = eINSTANCE.getFunctionalChain();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute FUNCTIONAL_CHAIN__KIND = eINSTANCE.getFunctionalChain_Kind();

		/**
     * The meta object literal for the '<em><b>Owned Functional Chain Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS = eINSTANCE.getFunctionalChain_OwnedFunctionalChainInvolvements();

		/**
     * The meta object literal for the '<em><b>Owned Functional Chain Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS = eINSTANCE.getFunctionalChain_OwnedFunctionalChainRealizations();

		/**
     * The meta object literal for the '<em><b>Involved Functional Chain Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS = eINSTANCE.getFunctionalChain_InvolvedFunctionalChainInvolvements();

		/**
     * The meta object literal for the '<em><b>Involved Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS = eINSTANCE.getFunctionalChain_InvolvedFunctions();

		/**
     * The meta object literal for the '<em><b>Involved Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES = eINSTANCE.getFunctionalChain_InvolvedFunctionalExchanges();

		/**
     * The meta object literal for the '<em><b>Involved Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS = eINSTANCE.getFunctionalChain_InvolvedElements();

		/**
     * The meta object literal for the '<em><b>Enacted Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS = eINSTANCE.getFunctionalChain_EnactedFunctions();

		/**
     * The meta object literal for the '<em><b>Enacted Functional Blocks</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS = eINSTANCE.getFunctionalChain_EnactedFunctionalBlocks();

		/**
     * The meta object literal for the '<em><b>Available In States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES = eINSTANCE.getFunctionalChain_AvailableInStates();

		/**
     * The meta object literal for the '<em><b>First Functional Chain Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS = eINSTANCE.getFunctionalChain_FirstFunctionalChainInvolvements();

		/**
     * The meta object literal for the '<em><b>Involving Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES = eINSTANCE.getFunctionalChain_InvolvingCapabilities();

		/**
     * The meta object literal for the '<em><b>Involving Capability Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS = eINSTANCE.getFunctionalChain_InvolvingCapabilityRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Functional Chains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS = eINSTANCE.getFunctionalChain_RealizedFunctionalChains();

		/**
     * The meta object literal for the '<em><b>Realizing Functional Chains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS = eINSTANCE.getFunctionalChain_RealizingFunctionalChains();

		/**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__PRE_CONDITION = eINSTANCE.getFunctionalChain_PreCondition();

		/**
     * The meta object literal for the '<em><b>Post Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__POST_CONDITION = eINSTANCE.getFunctionalChain_PostCondition();

		/**
     * The meta object literal for the '<em><b>Owned Sequence Nodes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES = eINSTANCE.getFunctionalChain_OwnedSequenceNodes();

		/**
     * The meta object literal for the '<em><b>Owned Sequence Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS = eINSTANCE.getFunctionalChain_OwnedSequenceLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalChainContainerImpl <em>Abstract Functional Chain Container</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionalChainContainerImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionalChainContainer()
     * @generated
     */
		EClass ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER = eINSTANCE.getAbstractFunctionalChainContainer();

		/**
     * The meta object literal for the '<em><b>Owned Functional Chains</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS = eINSTANCE.getAbstractFunctionalChainContainer_OwnedFunctionalChains();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl <em>Functional Chain Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainInvolvement()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN_INVOLVEMENT = eINSTANCE.getFunctionalChainInvolvement();

		/**
     * The meta object literal for the '<em><b>Next Functional Chain Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS = eINSTANCE.getFunctionalChainInvolvement_NextFunctionalChainInvolvements();

		/**
     * The meta object literal for the '<em><b>Previous Functional Chain Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS = eINSTANCE.getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements();

		/**
     * The meta object literal for the '<em><b>Involved Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT = eINSTANCE.getFunctionalChainInvolvement_InvolvedElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainReferenceImpl <em>Functional Chain Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainReferenceImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainReference()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN_REFERENCE = eINSTANCE.getFunctionalChainReference();

		/**
     * The meta object literal for the '<em><b>Referenced Functional Chain</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN = eINSTANCE.getFunctionalChainReference_ReferencedFunctionalChain();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionInputPortImpl <em>Function Input Port</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionInputPortImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionInputPort()
     * @generated
     */
		EClass FUNCTION_INPUT_PORT = eINSTANCE.getFunctionInputPort();

		/**
     * The meta object literal for the '<em><b>Incoming Exchange Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS = eINSTANCE.getFunctionInputPort_IncomingExchangeItems();

		/**
     * The meta object literal for the '<em><b>Incoming Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_INPUT_PORT__INCOMING_FUNCTIONAL_EXCHANGES = eINSTANCE.getFunctionInputPort_IncomingFunctionalExchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionOutputPortImpl <em>Function Output Port</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionOutputPortImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionOutputPort()
     * @generated
     */
		EClass FUNCTION_OUTPUT_PORT = eINSTANCE.getFunctionOutputPort();

		/**
     * The meta object literal for the '<em><b>Outgoing Exchange Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS = eINSTANCE.getFunctionOutputPort_OutgoingExchangeItems();

		/**
     * The meta object literal for the '<em><b>Outgoing Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_OUTPUT_PORT__OUTGOING_FUNCTIONAL_EXCHANGES = eINSTANCE.getFunctionOutputPort_OutgoingFunctionalExchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.AbstractFunctionAllocation <em>Abstract Function Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.AbstractFunctionAllocation
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunctionAllocation()
     * @generated
     */
		EClass ABSTRACT_FUNCTION_ALLOCATION = eINSTANCE.getAbstractFunctionAllocation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentFunctionalAllocationImpl <em>Component Functional Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentFunctionalAllocationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentFunctionalAllocation()
     * @generated
     */
		EClass COMPONENT_FUNCTIONAL_ALLOCATION = eINSTANCE.getComponentFunctionalAllocation();

		/**
     * The meta object literal for the '<em><b>Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_FUNCTIONAL_ALLOCATION__FUNCTION = eINSTANCE.getComponentFunctionalAllocation_Function();

		/**
     * The meta object literal for the '<em><b>Block</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_FUNCTIONAL_ALLOCATION__BLOCK = eINSTANCE.getComponentFunctionalAllocation_Block();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainRealizationImpl <em>Functional Chain Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainRealizationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainRealization()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN_REALIZATION = eINSTANCE.getFunctionalChainRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationRealizationImpl <em>Exchange Specification Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ExchangeSpecificationRealizationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getExchangeSpecificationRealization()
     * @generated
     */
		EClass EXCHANGE_SPECIFICATION_REALIZATION = eINSTANCE.getExchangeSpecificationRealization();

		/**
     * The meta object literal for the '<em><b>Realized Exchange Specification</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION = eINSTANCE.getExchangeSpecificationRealization_RealizedExchangeSpecification();

		/**
     * The meta object literal for the '<em><b>Realizing Exchange Specification</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_SPECIFICATION_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION = eINSTANCE.getExchangeSpecificationRealization_RealizingExchangeSpecification();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeRealizationImpl <em>Functional Exchange Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalExchangeRealizationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalExchangeRealization()
     * @generated
     */
		EClass FUNCTIONAL_EXCHANGE_REALIZATION = eINSTANCE.getFunctionalExchangeRealization();

		/**
     * The meta object literal for the '<em><b>Realized Functional Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE = eINSTANCE.getFunctionalExchangeRealization_RealizedFunctionalExchange();

		/**
     * The meta object literal for the '<em><b>Realizing Functional Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE = eINSTANCE.getFunctionalExchangeRealization_RealizingFunctionalExchange();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionRealizationImpl <em>Function Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionRealizationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionRealization()
     * @generated
     */
		EClass FUNCTION_REALIZATION = eINSTANCE.getFunctionRealization();

		/**
     * The meta object literal for the '<em><b>Allocated Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_REALIZATION__ALLOCATED_FUNCTION = eINSTANCE.getFunctionRealization_AllocatedFunction();

		/**
     * The meta object literal for the '<em><b>Allocating Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_REALIZATION__ALLOCATING_FUNCTION = eINSTANCE.getFunctionRealization_AllocatingFunction();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalExchangeImpl <em>Functional Exchange</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalExchangeImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalExchange()
     * @generated
     */
		EClass FUNCTIONAL_EXCHANGE = eINSTANCE.getFunctionalExchange();

		/**
     * The meta object literal for the '<em><b>Exchange Specifications</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__EXCHANGE_SPECIFICATIONS = eINSTANCE.getFunctionalExchange_ExchangeSpecifications();

		/**
     * The meta object literal for the '<em><b>Involving Functional Chains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__INVOLVING_FUNCTIONAL_CHAINS = eINSTANCE.getFunctionalExchange_InvolvingFunctionalChains();

		/**
     * The meta object literal for the '<em><b>Exchanged Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS = eINSTANCE.getFunctionalExchange_ExchangedItems();

		/**
     * The meta object literal for the '<em><b>Allocating Component Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__ALLOCATING_COMPONENT_EXCHANGES = eINSTANCE.getFunctionalExchange_AllocatingComponentExchanges();

		/**
     * The meta object literal for the '<em><b>Incoming Component Exchange Functional Exchange Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_REALIZATIONS = eINSTANCE.getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Incoming Functional Exchange Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__INCOMING_FUNCTIONAL_EXCHANGE_REALIZATIONS = eINSTANCE.getFunctionalExchange_IncomingFunctionalExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Outgoing Functional Exchange Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__OUTGOING_FUNCTIONAL_EXCHANGE_REALIZATIONS = eINSTANCE.getFunctionalExchange_OutgoingFunctionalExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__CATEGORIES = eINSTANCE.getFunctionalExchange_Categories();

		/**
     * The meta object literal for the '<em><b>Owned Functional Exchange Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS = eINSTANCE.getFunctionalExchange_OwnedFunctionalExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Source Function Output Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT = eINSTANCE.getFunctionalExchange_SourceFunctionOutputPort();

		/**
     * The meta object literal for the '<em><b>Target Function Input Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT = eINSTANCE.getFunctionalExchange_TargetFunctionInputPort();

		/**
     * The meta object literal for the '<em><b>Realized Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__REALIZED_FUNCTIONAL_EXCHANGES = eINSTANCE.getFunctionalExchange_RealizedFunctionalExchanges();

		/**
     * The meta object literal for the '<em><b>Realizing Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_EXCHANGE__REALIZING_FUNCTIONAL_EXCHANGES = eINSTANCE.getFunctionalExchange_RealizingFunctionalExchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl <em>Abstract Function</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getAbstractFunction()
     * @generated
     */
		EClass ABSTRACT_FUNCTION = eINSTANCE.getAbstractFunction();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_FUNCTION__KIND = eINSTANCE.getAbstractFunction_Kind();

		/**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_FUNCTION__CONDITION = eINSTANCE.getAbstractFunction_Condition();

		/**
     * The meta object literal for the '<em><b>Owned Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__OWNED_FUNCTIONS = eINSTANCE.getAbstractFunction_OwnedFunctions();

		/**
     * The meta object literal for the '<em><b>Owned Function Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS = eINSTANCE.getAbstractFunction_OwnedFunctionRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Functional Exchanges</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES = eINSTANCE.getAbstractFunction_OwnedFunctionalExchanges();

		/**
     * The meta object literal for the '<em><b>Sub Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__SUB_FUNCTIONS = eINSTANCE.getAbstractFunction_SubFunctions();

		/**
     * The meta object literal for the '<em><b>Out Function Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS = eINSTANCE.getAbstractFunction_OutFunctionRealizations();

		/**
     * The meta object literal for the '<em><b>In Function Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS = eINSTANCE.getAbstractFunction_InFunctionRealizations();

		/**
     * The meta object literal for the '<em><b>Component Functional Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS = eINSTANCE.getAbstractFunction_ComponentFunctionalAllocations();

		/**
     * The meta object literal for the '<em><b>Allocation Blocks</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__ALLOCATION_BLOCKS = eINSTANCE.getAbstractFunction_AllocationBlocks();

		/**
     * The meta object literal for the '<em><b>Available In States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__AVAILABLE_IN_STATES = eINSTANCE.getAbstractFunction_AvailableInStates();

		/**
     * The meta object literal for the '<em><b>Involving Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES = eINSTANCE.getAbstractFunction_InvolvingCapabilities();

		/**
     * The meta object literal for the '<em><b>Involving Capability Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS = eINSTANCE.getAbstractFunction_InvolvingCapabilityRealizations();

		/**
     * The meta object literal for the '<em><b>Involving Functional Chains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS = eINSTANCE.getAbstractFunction_InvolvingFunctionalChains();

		/**
     * The meta object literal for the '<em><b>Linked State Machine</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__LINKED_STATE_MACHINE = eINSTANCE.getAbstractFunction_LinkedStateMachine();

		/**
     * The meta object literal for the '<em><b>Linked Function Specification</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION = eINSTANCE.getAbstractFunction_LinkedFunctionSpecification();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionPortImpl <em>Function Port</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionPortImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionPort()
     * @generated
     */
		EClass FUNCTION_PORT = eINSTANCE.getFunctionPort();

		/**
     * The meta object literal for the '<em><b>Represented Component Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
     * @generated
     */
		@Deprecated
		EReference FUNCTION_PORT__REPRESENTED_COMPONENT_PORT = eINSTANCE.getFunctionPort_RepresentedComponentPort();

		/**
     * The meta object literal for the '<em><b>Allocator Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS = eINSTANCE.getFunctionPort_AllocatorComponentPorts();

		/**
     * The meta object literal for the '<em><b>Realized Function Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PORT__REALIZED_FUNCTION_PORTS = eINSTANCE.getFunctionPort_RealizedFunctionPorts();

		/**
     * The meta object literal for the '<em><b>Realizing Function Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTION_PORT__REALIZING_FUNCTION_PORTS = eINSTANCE.getFunctionPort_RealizingFunctionPorts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeImpl <em>Component Exchange</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchange()
     * @generated
     */
		EClass COMPONENT_EXCHANGE = eINSTANCE.getComponentExchange();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMPONENT_EXCHANGE__KIND = eINSTANCE.getComponentExchange_Kind();

		/**
     * The meta object literal for the '<em><b>Oriented</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMPONENT_EXCHANGE__ORIENTED = eINSTANCE.getComponentExchange_Oriented();

		/**
     * The meta object literal for the '<em><b>Allocated Functional Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__ALLOCATED_FUNCTIONAL_EXCHANGES = eINSTANCE.getComponentExchange_AllocatedFunctionalExchanges();

		/**
     * The meta object literal for the '<em><b>Incoming Component Exchange Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_REALIZATIONS = eINSTANCE.getComponentExchange_IncomingComponentExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Outgoing Component Exchange Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_REALIZATIONS = eINSTANCE.getComponentExchange_OutgoingComponentExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Outgoing Component Exchange Functional Exchange Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = eINSTANCE.getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Functional Exchange Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = eINSTANCE.getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = eINSTANCE.getComponentExchange_OwnedComponentExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Ends</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS = eINSTANCE.getComponentExchange_OwnedComponentExchangeEnds();

		/**
     * The meta object literal for the '<em><b>Source Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__SOURCE_PORT = eINSTANCE.getComponentExchange_SourcePort();

		/**
     * The meta object literal for the '<em><b>Source Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__SOURCE_PART = eINSTANCE.getComponentExchange_SourcePart();

		/**
     * The meta object literal for the '<em><b>Target Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__TARGET_PORT = eINSTANCE.getComponentExchange_TargetPort();

		/**
     * The meta object literal for the '<em><b>Target Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__TARGET_PART = eINSTANCE.getComponentExchange_TargetPart();

		/**
     * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__CATEGORIES = eINSTANCE.getComponentExchange_Categories();

		/**
     * The meta object literal for the '<em><b>Allocator Physical Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__ALLOCATOR_PHYSICAL_LINKS = eINSTANCE.getComponentExchange_AllocatorPhysicalLinks();

		/**
     * The meta object literal for the '<em><b>Realized Component Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__REALIZED_COMPONENT_EXCHANGES = eINSTANCE.getComponentExchange_RealizedComponentExchanges();

		/**
     * The meta object literal for the '<em><b>Realizing Component Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE__REALIZING_COMPONENT_EXCHANGES = eINSTANCE.getComponentExchange_RealizingComponentExchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocationImpl <em>Component Exchange Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeAllocation()
     * @generated
     */
		EClass COMPONENT_EXCHANGE_ALLOCATION = eINSTANCE.getComponentExchangeAllocation();

		/**
     * The meta object literal for the '<em><b>Component Exchange Allocated</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED = eINSTANCE.getComponentExchangeAllocation_ComponentExchangeAllocated();

		/**
     * The meta object literal for the '<em><b>Component Exchange Allocator</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR = eINSTANCE.getComponentExchangeAllocation_ComponentExchangeAllocator();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocatorImpl <em>Component Exchange Allocator</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeAllocatorImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeAllocator()
     * @generated
     */
		EClass COMPONENT_EXCHANGE_ALLOCATOR = eINSTANCE.getComponentExchangeAllocator();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS = eINSTANCE.getComponentExchangeAllocator_OwnedComponentExchangeAllocations();

		/**
     * The meta object literal for the '<em><b>Allocated Component Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_ALLOCATOR__ALLOCATED_COMPONENT_EXCHANGES = eINSTANCE.getComponentExchangeAllocator_AllocatedComponentExchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeCategoryImpl <em>Component Exchange Category</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeCategoryImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeCategory()
     * @generated
     */
		EClass COMPONENT_EXCHANGE_CATEGORY = eINSTANCE.getComponentExchangeCategory();

		/**
     * The meta object literal for the '<em><b>Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_CATEGORY__EXCHANGES = eINSTANCE.getComponentExchangeCategory_Exchanges();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeEndImpl <em>Component Exchange End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeEndImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeEnd()
     * @generated
     */
		EClass COMPONENT_EXCHANGE_END = eINSTANCE.getComponentExchangeEnd();

		/**
     * The meta object literal for the '<em><b>Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_END__PORT = eINSTANCE.getComponentExchangeEnd_Port();

		/**
     * The meta object literal for the '<em><b>Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_END__PART = eINSTANCE.getComponentExchangeEnd_Part();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeFunctionalExchangeAllocationImpl <em>Component Exchange Functional Exchange Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeFunctionalExchangeAllocationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeFunctionalExchangeAllocation()
     * @generated
     */
		EClass COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION = eINSTANCE.getComponentExchangeFunctionalExchangeAllocation();

		/**
     * The meta object literal for the '<em><b>Allocated Functional Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATED_FUNCTIONAL_EXCHANGE = eINSTANCE.getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange();

		/**
     * The meta object literal for the '<em><b>Allocating Component Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATING_COMPONENT_EXCHANGE = eINSTANCE.getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentExchangeRealizationImpl <em>Component Exchange Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentExchangeRealizationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeRealization()
     * @generated
     */
		EClass COMPONENT_EXCHANGE_REALIZATION = eINSTANCE.getComponentExchangeRealization();

		/**
     * The meta object literal for the '<em><b>Allocated Component Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_REALIZATION__ALLOCATED_COMPONENT_EXCHANGE = eINSTANCE.getComponentExchangeRealization_AllocatedComponentExchange();

		/**
     * The meta object literal for the '<em><b>Allocating Component Exchange</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_EXCHANGE_REALIZATION__ALLOCATING_COMPONENT_EXCHANGE = eINSTANCE.getComponentExchangeRealization_AllocatingComponentExchange();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentPortImpl <em>Component Port</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentPortImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPort()
     * @generated
     */
		EClass COMPONENT_PORT = eINSTANCE.getComponentPort();

		/**
     * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMPONENT_PORT__ORIENTATION = eINSTANCE.getComponentPort_Orientation();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMPONENT_PORT__KIND = eINSTANCE.getComponentPort_Kind();

		/**
     * The meta object literal for the '<em><b>Component Exchanges</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__COMPONENT_EXCHANGES = eINSTANCE.getComponentPort_ComponentExchanges();

		/**
     * The meta object literal for the '<em><b>Allocated Function Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__ALLOCATED_FUNCTION_PORTS = eINSTANCE.getComponentPort_AllocatedFunctionPorts();

		/**
     * The meta object literal for the '<em><b>Delegated Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__DELEGATED_COMPONENT_PORTS = eINSTANCE.getComponentPort_DelegatedComponentPorts();

		/**
     * The meta object literal for the '<em><b>Delegating Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__DELEGATING_COMPONENT_PORTS = eINSTANCE.getComponentPort_DelegatingComponentPorts();

		/**
     * The meta object literal for the '<em><b>Allocating Physical Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__ALLOCATING_PHYSICAL_PORTS = eINSTANCE.getComponentPort_AllocatingPhysicalPorts();

		/**
     * The meta object literal for the '<em><b>Realized Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__REALIZED_COMPONENT_PORTS = eINSTANCE.getComponentPort_RealizedComponentPorts();

		/**
     * The meta object literal for the '<em><b>Realizing Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT__REALIZING_COMPONENT_PORTS = eINSTANCE.getComponentPort_RealizingComponentPorts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationImpl <em>Component Port Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPortAllocation()
     * @generated
     */
		EClass COMPONENT_PORT_ALLOCATION = eINSTANCE.getComponentPortAllocation();

		/**
     * The meta object literal for the '<em><b>Owned Component Port Allocation Ends</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT_ALLOCATION__OWNED_COMPONENT_PORT_ALLOCATION_ENDS = eINSTANCE.getComponentPortAllocation_OwnedComponentPortAllocationEnds();

		/**
     * The meta object literal for the '<em><b>Allocated Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT_ALLOCATION__ALLOCATED_PORT = eINSTANCE.getComponentPortAllocation_AllocatedPort();

		/**
     * The meta object literal for the '<em><b>Allocating Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT_ALLOCATION__ALLOCATING_PORT = eINSTANCE.getComponentPortAllocation_AllocatingPort();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationEndImpl <em>Component Port Allocation End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ComponentPortAllocationEndImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPortAllocationEnd()
     * @generated
     */
		EClass COMPONENT_PORT_ALLOCATION_END = eINSTANCE.getComponentPortAllocationEnd();

		/**
     * The meta object literal for the '<em><b>Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT_ALLOCATION_END__PORT = eINSTANCE.getComponentPortAllocationEnd_Port();

		/**
     * The meta object literal for the '<em><b>Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT_ALLOCATION_END__PART = eINSTANCE.getComponentPortAllocationEnd_Part();

		/**
     * The meta object literal for the '<em><b>Owning Component Port Allocation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PORT_ALLOCATION_END__OWNING_COMPONENT_PORT_ALLOCATION = eINSTANCE.getComponentPortAllocationEnd_OwningComponentPortAllocation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl <em>Functional Chain Involvement Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementLinkImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainInvolvementLink()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN_INVOLVEMENT_LINK = eINSTANCE.getFunctionalChainInvolvementLink();

		/**
     * The meta object literal for the '<em><b>Exchange Context</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT = eINSTANCE.getFunctionalChainInvolvementLink_ExchangeContext();

		/**
     * The meta object literal for the '<em><b>Exchanged Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS = eINSTANCE.getFunctionalChainInvolvementLink_ExchangedItems();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE = eINSTANCE.getFunctionalChainInvolvementLink_Source();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET = eINSTANCE.getFunctionalChainInvolvementLink_Target();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl <em>Sequence Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getSequenceLink()
     * @generated
     */
		EClass SEQUENCE_LINK = eINSTANCE.getSequenceLink();

		/**
     * The meta object literal for the '<em><b>Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_LINK__CONDITION = eINSTANCE.getSequenceLink_Condition();

		/**
     * The meta object literal for the '<em><b>Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_LINK__LINKS = eINSTANCE.getSequenceLink_Links();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_LINK__SOURCE = eINSTANCE.getSequenceLink_Source();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_LINK__TARGET = eINSTANCE.getSequenceLink_Target();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.SequenceLinkEnd <em>Sequence Link End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.SequenceLinkEnd
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getSequenceLinkEnd()
     * @generated
     */
		EClass SEQUENCE_LINK_END = eINSTANCE.getSequenceLinkEnd();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementFunctionImpl <em>Functional Chain Involvement Function</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.FunctionalChainInvolvementFunctionImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainInvolvementFunction()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION = eINSTANCE.getFunctionalChainInvolvementFunction();

		/**
     * The meta object literal for the '<em><b>Outgoing Involvement Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OUTGOING_INVOLVEMENT_LINKS = eINSTANCE.getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks();

		/**
     * The meta object literal for the '<em><b>Incoming Involvement Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INCOMING_INVOLVEMENT_LINKS = eINSTANCE.getFunctionalChainInvolvementFunction_IncomingInvolvementLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.impl.ControlNodeImpl <em>Control Node</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.impl.ControlNodeImpl
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getControlNode()
     * @generated
     */
		EClass CONTROL_NODE = eINSTANCE.getControlNode();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CONTROL_NODE__KIND = eINSTANCE.getControlNode_Kind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext <em>Reference Hierarchy Context</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.ReferenceHierarchyContext
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getReferenceHierarchyContext()
     * @generated
     */
		EClass REFERENCE_HIERARCHY_CONTEXT = eINSTANCE.getReferenceHierarchyContext();

		/**
     * The meta object literal for the '<em><b>Source Reference Hierarchy</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY = eINSTANCE.getReferenceHierarchyContext_SourceReferenceHierarchy();

		/**
     * The meta object literal for the '<em><b>Target Reference Hierarchy</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY = eINSTANCE.getReferenceHierarchyContext_TargetReferenceHierarchy();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.FunctionalChainKind <em>Functional Chain Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.FunctionalChainKind
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionalChainKind()
     * @generated
     */
		EEnum FUNCTIONAL_CHAIN_KIND = eINSTANCE.getFunctionalChainKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.FunctionKind <em>Function Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.FunctionKind
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getFunctionKind()
     * @generated
     */
		EEnum FUNCTION_KIND = eINSTANCE.getFunctionKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.ComponentExchangeKind <em>Component Exchange Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.ComponentExchangeKind
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentExchangeKind()
     * @generated
     */
		EEnum COMPONENT_EXCHANGE_KIND = eINSTANCE.getComponentExchangeKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.ComponentPortKind <em>Component Port Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.ComponentPortKind
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getComponentPortKind()
     * @generated
     */
		EEnum COMPONENT_PORT_KIND = eINSTANCE.getComponentPortKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.OrientationPortKind <em>Orientation Port Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.OrientationPortKind
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getOrientationPortKind()
     * @generated
     */
		EEnum ORIENTATION_PORT_KIND = eINSTANCE.getOrientationPortKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.fa.ControlNodeKind <em>Control Node Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.fa.ControlNodeKind
     * @see org.polarsys.capella.core.data.fa.impl.FaPackageImpl#getControlNodeKind()
     * @generated
     */
		EEnum CONTROL_NODE_KIND = eINSTANCE.getControlNodeKind();

	}

} //FaPackage
