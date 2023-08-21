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
package org.polarsys.capella.core.data.sharedmodel;

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
 * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Shared aims at defining a structure for the shared model elements storage. It is dedicated to the model elements reuse. This is a rest of MDSysE and may be reengineered into more adapted solutions.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='n/a' usage\040examples='none' constraints='This package depends on the model CapellaModeller.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface SharedmodelPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "sharedmodel"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/sharedmodel/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.sharedmodel"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	SharedmodelPackage eINSTANCE = org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl <em>Shared Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl
   * @see org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl#getSharedPkg()
   * @generated
   */
	int SHARED_PKG = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_EXTENSIONS = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__ID = CapellacorePackage.REUSEABLE_STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__SID = CapellacorePackage.REUSEABLE_STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__CONSTRAINTS = CapellacorePackage.REUSEABLE_STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_CONSTRAINTS = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__NAME = CapellacorePackage.REUSEABLE_STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__INCOMING_TRACES = CapellacorePackage.REUSEABLE_STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OUTGOING_TRACES = CapellacorePackage.REUSEABLE_STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__VISIBLE_IN_DOC = CapellacorePackage.REUSEABLE_STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__VISIBLE_IN_LM = CapellacorePackage.REUSEABLE_STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__SUMMARY = CapellacorePackage.REUSEABLE_STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__DESCRIPTION = CapellacorePackage.REUSEABLE_STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__REVIEW = CapellacorePackage.REUSEABLE_STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.REUSEABLE_STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.REUSEABLE_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__STATUS = CapellacorePackage.REUSEABLE_STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__FEATURES = CapellacorePackage.REUSEABLE_STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_TRACES = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.REUSEABLE_STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__NAMING_RULES = CapellacorePackage.REUSEABLE_STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.REUSEABLE_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Reuse Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__REUSE_LINKS = CapellacorePackage.REUSEABLE_STRUCTURE__REUSE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_DATA_PKG = CapellacorePackage.REUSEABLE_STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Generic Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG__OWNED_GENERIC_PKG = CapellacorePackage.REUSEABLE_STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Shared Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SHARED_PKG_FEATURE_COUNT = CapellacorePackage.REUSEABLE_STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.sharedmodel.impl.GenericPkgImpl <em>Generic Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.sharedmodel.impl.GenericPkgImpl
   * @see org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl#getGenericPkg()
   * @generated
   */
	int GENERIC_PKG = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Sub Generic Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__SUB_GENERIC_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Capella Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG__CAPELLA_ELEMENTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Generic Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERIC_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg <em>Shared Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Shared Pkg</em>'.
   * @see org.polarsys.capella.core.data.sharedmodel.SharedPkg
   * @generated
   */
	EClass getSharedPkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedDataPkg <em>Owned Data Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Data Pkg</em>'.
   * @see org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedDataPkg()
   * @see #getSharedPkg()
   * @generated
   */
	EReference getSharedPkg_OwnedDataPkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedGenericPkg <em>Owned Generic Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Generic Pkg</em>'.
   * @see org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedGenericPkg()
   * @see #getSharedPkg()
   * @generated
   */
	EReference getSharedPkg_OwnedGenericPkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.sharedmodel.GenericPkg <em>Generic Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generic Pkg</em>'.
   * @see org.polarsys.capella.core.data.sharedmodel.GenericPkg
   * @generated
   */
	EClass getGenericPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.sharedmodel.GenericPkg#getSubGenericPkgs <em>Sub Generic Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sub Generic Pkgs</em>'.
   * @see org.polarsys.capella.core.data.sharedmodel.GenericPkg#getSubGenericPkgs()
   * @see #getGenericPkg()
   * @generated
   */
	EReference getGenericPkg_SubGenericPkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.sharedmodel.GenericPkg#getCapellaElements <em>Capella Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Capella Elements</em>'.
   * @see org.polarsys.capella.core.data.sharedmodel.GenericPkg#getCapellaElements()
   * @see #getGenericPkg()
   * @generated
   */
	EReference getGenericPkg_CapellaElements();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	SharedmodelFactory getSharedmodelFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl <em>Shared Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.sharedmodel.impl.SharedPkgImpl
     * @see org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl#getSharedPkg()
     * @generated
     */
		EClass SHARED_PKG = eINSTANCE.getSharedPkg();

		/**
     * The meta object literal for the '<em><b>Owned Data Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SHARED_PKG__OWNED_DATA_PKG = eINSTANCE.getSharedPkg_OwnedDataPkg();

		/**
     * The meta object literal for the '<em><b>Owned Generic Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SHARED_PKG__OWNED_GENERIC_PKG = eINSTANCE.getSharedPkg_OwnedGenericPkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.sharedmodel.impl.GenericPkgImpl <em>Generic Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.sharedmodel.impl.GenericPkgImpl
     * @see org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl#getGenericPkg()
     * @generated
     */
		EClass GENERIC_PKG = eINSTANCE.getGenericPkg();

		/**
     * The meta object literal for the '<em><b>Sub Generic Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERIC_PKG__SUB_GENERIC_PKGS = eINSTANCE.getGenericPkg_SubGenericPkgs();

		/**
     * The meta object literal for the '<em><b>Capella Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERIC_PKG__CAPELLA_ELEMENTS = eINSTANCE.getGenericPkg_CapellaElements();

	}

} //SharedmodelPackage
