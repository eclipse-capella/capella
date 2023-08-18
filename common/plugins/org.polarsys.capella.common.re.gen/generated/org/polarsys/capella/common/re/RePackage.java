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
package org.polarsys.capella.common.re;

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
 * @see org.polarsys.capella.common.re.ReFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension extensibleProviderFactory='true' childCreationExtenders='true'"
 * @generated
 */
public interface RePackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "re"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/common/re/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "re"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	RePackage eINSTANCE = org.polarsys.capella.common.re.impl.RePackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.ReAbstractElementImpl <em>Abstract Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.ReAbstractElementImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReAbstractElement()
   * @generated
   */
	int RE_ABSTRACT_ELEMENT = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_ABSTRACT_ELEMENT__OWNED_EXTENSIONS = EmdePackage.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_ABSTRACT_ELEMENT__ID = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_ABSTRACT_ELEMENT_FEATURE_COUNT = EmdePackage.EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.ReNamedElementImpl <em>Named Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.ReNamedElementImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReNamedElement()
   * @generated
   */
	int RE_NAMED_ELEMENT = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_NAMED_ELEMENT__OWNED_EXTENSIONS = RE_ABSTRACT_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_NAMED_ELEMENT__ID = RE_ABSTRACT_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_NAMED_ELEMENT__NAME = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Named Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_NAMED_ELEMENT_FEATURE_COUNT = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.ReDescriptionElementImpl <em>Description Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.ReDescriptionElementImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReDescriptionElement()
   * @generated
   */
	int RE_DESCRIPTION_ELEMENT = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_DESCRIPTION_ELEMENT__OWNED_EXTENSIONS = RE_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_DESCRIPTION_ELEMENT__ID = RE_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_DESCRIPTION_ELEMENT__NAME = RE_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_DESCRIPTION_ELEMENT__DESCRIPTION = RE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Description Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_DESCRIPTION_ELEMENT_FEATURE_COUNT = RE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.ReElementContainer <em>Element Container</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.ReElementContainer
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReElementContainer()
   * @generated
   */
	int RE_ELEMENT_CONTAINER = 3;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_ELEMENT_CONTAINER__OWNED_ELEMENTS = 0;

	/**
   * The number of structural features of the '<em>Element Container</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RE_ELEMENT_CONTAINER_FEATURE_COUNT = 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.CatalogElementPkgImpl <em>Catalog Element Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.CatalogElementPkgImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElementPkg()
   * @generated
   */
	int CATALOG_ELEMENT_PKG = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_PKG__OWNED_EXTENSIONS = RE_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_PKG__ID = RE_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_PKG__NAME = RE_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_PKG__OWNED_ELEMENTS = RE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Element Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS = RE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Catalog Element Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_PKG_FEATURE_COUNT = RE_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.RecCatalogImpl <em>Rec Catalog</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.RecCatalogImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getRecCatalog()
   * @generated
   */
	int REC_CATALOG = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG__OWNED_EXTENSIONS = CATALOG_ELEMENT_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG__ID = CATALOG_ELEMENT_PKG__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG__NAME = CATALOG_ELEMENT_PKG__NAME;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG__OWNED_ELEMENTS = CATALOG_ELEMENT_PKG__OWNED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Element Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG__OWNED_ELEMENT_PKGS = CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS;

	/**
   * The feature id for the '<em><b>Owned Compliancy Definition Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG__OWNED_COMPLIANCY_DEFINITION_PKG = CATALOG_ELEMENT_PKG_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Rec Catalog</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REC_CATALOG_FEATURE_COUNT = CATALOG_ELEMENT_PKG_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.GroupingElementPkgImpl <em>Grouping Element Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.GroupingElementPkgImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getGroupingElementPkg()
   * @generated
   */
	int GROUPING_ELEMENT_PKG = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GROUPING_ELEMENT_PKG__OWNED_EXTENSIONS = CATALOG_ELEMENT_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GROUPING_ELEMENT_PKG__ID = CATALOG_ELEMENT_PKG__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GROUPING_ELEMENT_PKG__NAME = CATALOG_ELEMENT_PKG__NAME;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GROUPING_ELEMENT_PKG__OWNED_ELEMENTS = CATALOG_ELEMENT_PKG__OWNED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Element Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GROUPING_ELEMENT_PKG__OWNED_ELEMENT_PKGS = CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS;

	/**
   * The number of structural features of the '<em>Grouping Element Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GROUPING_ELEMENT_PKG_FEATURE_COUNT = CATALOG_ELEMENT_PKG_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl <em>Catalog Element Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.CatalogElementLinkImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElementLink()
   * @generated
   */
	int CATALOG_ELEMENT_LINK = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__OWNED_EXTENSIONS = RE_ABSTRACT_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__ID = RE_ABSTRACT_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__SOURCE = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__TARGET = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Origin</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__ORIGIN = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Unsynchronized Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Suffixed</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK__SUFFIXED = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Catalog Element Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_LINK_FEATURE_COUNT = RE_ABSTRACT_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.CatalogElementImpl <em>Catalog Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.CatalogElementImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElement()
   * @generated
   */
	int CATALOG_ELEMENT = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__OWNED_EXTENSIONS = RE_DESCRIPTION_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__ID = RE_DESCRIPTION_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__NAME = RE_DESCRIPTION_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__DESCRIPTION = RE_DESCRIPTION_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__OWNED_ELEMENTS = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__KIND = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Author</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__AUTHOR = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Environment</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__ENVIRONMENT = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATALOG_ELEMENT__SUFFIX = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Purpose</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__PURPOSE = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__READ_ONLY = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__VERSION = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Tags</b></em>' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__TAGS = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Origin</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__ORIGIN = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Current Compliancy</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__CURRENT_COMPLIANCY = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Default Replica Compliancy</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__OWNED_LINKS = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Referenced Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__REFERENCED_ELEMENTS = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Replicated Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT__REPLICATED_ELEMENTS = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The number of structural features of the '<em>Catalog Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CATALOG_ELEMENT_FEATURE_COUNT = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.CompliancyDefinitionPkgImpl <em>Compliancy Definition Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.CompliancyDefinitionPkgImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCompliancyDefinitionPkg()
   * @generated
   */
	int COMPLIANCY_DEFINITION_PKG = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION_PKG__OWNED_EXTENSIONS = RE_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION_PKG__ID = RE_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION_PKG__NAME = RE_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Owned Definitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS = RE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Compliancy Definition Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION_PKG_FEATURE_COUNT = RE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.impl.CompliancyDefinitionImpl <em>Compliancy Definition</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.impl.CompliancyDefinitionImpl
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCompliancyDefinition()
   * @generated
   */
	int COMPLIANCY_DEFINITION = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION__OWNED_EXTENSIONS = RE_DESCRIPTION_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION__ID = RE_DESCRIPTION_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION__NAME = RE_DESCRIPTION_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION__DESCRIPTION = RE_DESCRIPTION_ELEMENT__DESCRIPTION;

	/**
   * The number of structural features of the '<em>Compliancy Definition</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLIANCY_DEFINITION_FEATURE_COUNT = RE_DESCRIPTION_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.common.re.CatalogElementKind <em>Catalog Element Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.common.re.CatalogElementKind
   * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElementKind()
   * @generated
   */
	int CATALOG_ELEMENT_KIND = 11;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.ReAbstractElement <em>Abstract Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Element</em>'.
   * @see org.polarsys.capella.common.re.ReAbstractElement
   * @generated
   */
	EClass getReAbstractElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.ReAbstractElement#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.polarsys.capella.common.re.ReAbstractElement#getId()
   * @see #getReAbstractElement()
   * @generated
   */
	EAttribute getReAbstractElement_Id();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.ReNamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Element</em>'.
   * @see org.polarsys.capella.common.re.ReNamedElement
   * @generated
   */
	EClass getReNamedElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.ReNamedElement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.polarsys.capella.common.re.ReNamedElement#getName()
   * @see #getReNamedElement()
   * @generated
   */
	EAttribute getReNamedElement_Name();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.ReDescriptionElement <em>Description Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Description Element</em>'.
   * @see org.polarsys.capella.common.re.ReDescriptionElement
   * @generated
   */
	EClass getReDescriptionElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.ReDescriptionElement#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.polarsys.capella.common.re.ReDescriptionElement#getDescription()
   * @see #getReDescriptionElement()
   * @generated
   */
	EAttribute getReDescriptionElement_Description();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.ReElementContainer <em>Element Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Container</em>'.
   * @see org.polarsys.capella.common.re.ReElementContainer
   * @generated
   */
	EClass getReElementContainer();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.re.ReElementContainer#getOwnedElements <em>Owned Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Elements</em>'.
   * @see org.polarsys.capella.common.re.ReElementContainer#getOwnedElements()
   * @see #getReElementContainer()
   * @generated
   */
	EReference getReElementContainer_OwnedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.RecCatalog <em>Rec Catalog</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rec Catalog</em>'.
   * @see org.polarsys.capella.common.re.RecCatalog
   * @generated
   */
	EClass getRecCatalog();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.common.re.RecCatalog#getOwnedCompliancyDefinitionPkg <em>Owned Compliancy Definition Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Compliancy Definition Pkg</em>'.
   * @see org.polarsys.capella.common.re.RecCatalog#getOwnedCompliancyDefinitionPkg()
   * @see #getRecCatalog()
   * @generated
   */
	EReference getRecCatalog_OwnedCompliancyDefinitionPkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.GroupingElementPkg <em>Grouping Element Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Grouping Element Pkg</em>'.
   * @see org.polarsys.capella.common.re.GroupingElementPkg
   * @generated
   */
	EClass getGroupingElementPkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.CatalogElementPkg <em>Catalog Element Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Catalog Element Pkg</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementPkg
   * @generated
   */
	EClass getCatalogElementPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.re.CatalogElementPkg#getOwnedElementPkgs <em>Owned Element Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Element Pkgs</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementPkg#getOwnedElementPkgs()
   * @see #getCatalogElementPkg()
   * @generated
   */
	EReference getCatalogElementPkg_OwnedElementPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.CatalogElementLink <em>Catalog Element Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Catalog Element Link</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementLink
   * @generated
   */
	EClass getCatalogElementLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.re.CatalogElementLink#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementLink#getSource()
   * @see #getCatalogElementLink()
   * @generated
   */
	EReference getCatalogElementLink_Source();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.re.CatalogElementLink#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementLink#getTarget()
   * @see #getCatalogElementLink()
   * @generated
   */
	EReference getCatalogElementLink_Target();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.re.CatalogElementLink#getOrigin <em>Origin</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Origin</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementLink#getOrigin()
   * @see #getCatalogElementLink()
   * @generated
   */
	EReference getCatalogElementLink_Origin();

	/**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.common.re.CatalogElementLink#getUnsynchronizedFeatures <em>Unsynchronized Features</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Unsynchronized Features</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementLink#getUnsynchronizedFeatures()
   * @see #getCatalogElementLink()
   * @generated
   */
	EAttribute getCatalogElementLink_UnsynchronizedFeatures();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElementLink#isSuffixed <em>Suffixed</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suffixed</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementLink#isSuffixed()
   * @see #getCatalogElementLink()
   * @generated
   */
	EAttribute getCatalogElementLink_Suffixed();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.CatalogElement <em>Catalog Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Catalog Element</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement
   * @generated
   */
	EClass getCatalogElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getKind()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Author</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getAuthor()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_Author();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#getEnvironment <em>Environment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Environment</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getEnvironment()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_Environment();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#getSuffix <em>Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suffix</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getSuffix()
   * @see #getCatalogElement()
   * @generated
   */
  EAttribute getCatalogElement_Suffix();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#getPurpose <em>Purpose</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Purpose</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getPurpose()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_Purpose();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#isReadOnly <em>Read Only</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Read Only</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#isReadOnly()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_ReadOnly();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.common.re.CatalogElement#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getVersion()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_Version();

	/**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.common.re.CatalogElement#getTags <em>Tags</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Tags</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getTags()
   * @see #getCatalogElement()
   * @generated
   */
	EAttribute getCatalogElement_Tags();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.re.CatalogElement#getOrigin <em>Origin</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Origin</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getOrigin()
   * @see #getCatalogElement()
   * @generated
   */
	EReference getCatalogElement_Origin();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.re.CatalogElement#getCurrentCompliancy <em>Current Compliancy</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Current Compliancy</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getCurrentCompliancy()
   * @see #getCatalogElement()
   * @generated
   */
	EReference getCatalogElement_CurrentCompliancy();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.common.re.CatalogElement#getDefaultReplicaCompliancy <em>Default Replica Compliancy</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Default Replica Compliancy</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getDefaultReplicaCompliancy()
   * @see #getCatalogElement()
   * @generated
   */
	EReference getCatalogElement_DefaultReplicaCompliancy();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.re.CatalogElement#getOwnedLinks <em>Owned Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Links</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getOwnedLinks()
   * @see #getCatalogElement()
   * @generated
   */
	EReference getCatalogElement_OwnedLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.re.CatalogElement#getReferencedElements <em>Referenced Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced Elements</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getReferencedElements()
   * @see #getCatalogElement()
   * @generated
   */
	EReference getCatalogElement_ReferencedElements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.common.re.CatalogElement#getReplicatedElements <em>Replicated Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Replicated Elements</em>'.
   * @see org.polarsys.capella.common.re.CatalogElement#getReplicatedElements()
   * @see #getCatalogElement()
   * @generated
   */
	EReference getCatalogElement_ReplicatedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.CompliancyDefinitionPkg <em>Compliancy Definition Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compliancy Definition Pkg</em>'.
   * @see org.polarsys.capella.common.re.CompliancyDefinitionPkg
   * @generated
   */
	EClass getCompliancyDefinitionPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.common.re.CompliancyDefinitionPkg#getOwnedDefinitions <em>Owned Definitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Definitions</em>'.
   * @see org.polarsys.capella.common.re.CompliancyDefinitionPkg#getOwnedDefinitions()
   * @see #getCompliancyDefinitionPkg()
   * @generated
   */
	EReference getCompliancyDefinitionPkg_OwnedDefinitions();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.common.re.CompliancyDefinition <em>Compliancy Definition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compliancy Definition</em>'.
   * @see org.polarsys.capella.common.re.CompliancyDefinition
   * @generated
   */
	EClass getCompliancyDefinition();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.common.re.CatalogElementKind <em>Catalog Element Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Catalog Element Kind</em>'.
   * @see org.polarsys.capella.common.re.CatalogElementKind
   * @generated
   */
	EEnum getCatalogElementKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	ReFactory getReFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.ReAbstractElementImpl <em>Abstract Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.ReAbstractElementImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReAbstractElement()
     * @generated
     */
		EClass RE_ABSTRACT_ELEMENT = eINSTANCE.getReAbstractElement();

		/**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute RE_ABSTRACT_ELEMENT__ID = eINSTANCE.getReAbstractElement_Id();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.ReNamedElementImpl <em>Named Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.ReNamedElementImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReNamedElement()
     * @generated
     */
		EClass RE_NAMED_ELEMENT = eINSTANCE.getReNamedElement();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute RE_NAMED_ELEMENT__NAME = eINSTANCE.getReNamedElement_Name();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.ReDescriptionElementImpl <em>Description Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.ReDescriptionElementImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReDescriptionElement()
     * @generated
     */
		EClass RE_DESCRIPTION_ELEMENT = eINSTANCE.getReDescriptionElement();

		/**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute RE_DESCRIPTION_ELEMENT__DESCRIPTION = eINSTANCE.getReDescriptionElement_Description();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.ReElementContainer <em>Element Container</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.ReElementContainer
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getReElementContainer()
     * @generated
     */
		EClass RE_ELEMENT_CONTAINER = eINSTANCE.getReElementContainer();

		/**
     * The meta object literal for the '<em><b>Owned Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference RE_ELEMENT_CONTAINER__OWNED_ELEMENTS = eINSTANCE.getReElementContainer_OwnedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.RecCatalogImpl <em>Rec Catalog</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.RecCatalogImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getRecCatalog()
     * @generated
     */
		EClass REC_CATALOG = eINSTANCE.getRecCatalog();

		/**
     * The meta object literal for the '<em><b>Owned Compliancy Definition Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REC_CATALOG__OWNED_COMPLIANCY_DEFINITION_PKG = eINSTANCE.getRecCatalog_OwnedCompliancyDefinitionPkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.GroupingElementPkgImpl <em>Grouping Element Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.GroupingElementPkgImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getGroupingElementPkg()
     * @generated
     */
		EClass GROUPING_ELEMENT_PKG = eINSTANCE.getGroupingElementPkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.CatalogElementPkgImpl <em>Catalog Element Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.CatalogElementPkgImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElementPkg()
     * @generated
     */
		EClass CATALOG_ELEMENT_PKG = eINSTANCE.getCatalogElementPkg();

		/**
     * The meta object literal for the '<em><b>Owned Element Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS = eINSTANCE.getCatalogElementPkg_OwnedElementPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl <em>Catalog Element Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.CatalogElementLinkImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElementLink()
     * @generated
     */
		EClass CATALOG_ELEMENT_LINK = eINSTANCE.getCatalogElementLink();

		/**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT_LINK__SOURCE = eINSTANCE.getCatalogElementLink_Source();

		/**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT_LINK__TARGET = eINSTANCE.getCatalogElementLink_Target();

		/**
     * The meta object literal for the '<em><b>Origin</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT_LINK__ORIGIN = eINSTANCE.getCatalogElementLink_Origin();

		/**
     * The meta object literal for the '<em><b>Unsynchronized Features</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES = eINSTANCE.getCatalogElementLink_UnsynchronizedFeatures();

		/**
     * The meta object literal for the '<em><b>Suffixed</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT_LINK__SUFFIXED = eINSTANCE.getCatalogElementLink_Suffixed();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.CatalogElementImpl <em>Catalog Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.CatalogElementImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElement()
     * @generated
     */
		EClass CATALOG_ELEMENT = eINSTANCE.getCatalogElement();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__KIND = eINSTANCE.getCatalogElement_Kind();

		/**
     * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__AUTHOR = eINSTANCE.getCatalogElement_Author();

		/**
     * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__ENVIRONMENT = eINSTANCE.getCatalogElement_Environment();

		/**
     * The meta object literal for the '<em><b>Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATALOG_ELEMENT__SUFFIX = eINSTANCE.getCatalogElement_Suffix();

    /**
     * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__PURPOSE = eINSTANCE.getCatalogElement_Purpose();

		/**
     * The meta object literal for the '<em><b>Read Only</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__READ_ONLY = eINSTANCE.getCatalogElement_ReadOnly();

		/**
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__VERSION = eINSTANCE.getCatalogElement_Version();

		/**
     * The meta object literal for the '<em><b>Tags</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CATALOG_ELEMENT__TAGS = eINSTANCE.getCatalogElement_Tags();

		/**
     * The meta object literal for the '<em><b>Origin</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT__ORIGIN = eINSTANCE.getCatalogElement_Origin();

		/**
     * The meta object literal for the '<em><b>Current Compliancy</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT__CURRENT_COMPLIANCY = eINSTANCE.getCatalogElement_CurrentCompliancy();

		/**
     * The meta object literal for the '<em><b>Default Replica Compliancy</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY = eINSTANCE.getCatalogElement_DefaultReplicaCompliancy();

		/**
     * The meta object literal for the '<em><b>Owned Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT__OWNED_LINKS = eINSTANCE.getCatalogElement_OwnedLinks();

		/**
     * The meta object literal for the '<em><b>Referenced Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT__REFERENCED_ELEMENTS = eINSTANCE.getCatalogElement_ReferencedElements();

		/**
     * The meta object literal for the '<em><b>Replicated Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CATALOG_ELEMENT__REPLICATED_ELEMENTS = eINSTANCE.getCatalogElement_ReplicatedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.CompliancyDefinitionPkgImpl <em>Compliancy Definition Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.CompliancyDefinitionPkgImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCompliancyDefinitionPkg()
     * @generated
     */
		EClass COMPLIANCY_DEFINITION_PKG = eINSTANCE.getCompliancyDefinitionPkg();

		/**
     * The meta object literal for the '<em><b>Owned Definitions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS = eINSTANCE.getCompliancyDefinitionPkg_OwnedDefinitions();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.impl.CompliancyDefinitionImpl <em>Compliancy Definition</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.impl.CompliancyDefinitionImpl
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCompliancyDefinition()
     * @generated
     */
		EClass COMPLIANCY_DEFINITION = eINSTANCE.getCompliancyDefinition();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.common.re.CatalogElementKind <em>Catalog Element Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.common.re.CatalogElementKind
     * @see org.polarsys.capella.common.re.impl.RePackageImpl#getCatalogElementKind()
     * @generated
     */
		EEnum CATALOG_ELEMENT_KIND = eINSTANCE.getCatalogElementKind();

	}

} //RePackage
