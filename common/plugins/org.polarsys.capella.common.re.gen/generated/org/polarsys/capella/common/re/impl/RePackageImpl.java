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
package org.polarsys.capella.common.re.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.CompliancyDefinitionPkg;
import org.polarsys.capella.common.re.GroupingElementPkg;
import org.polarsys.capella.common.re.ReAbstractElement;
import org.polarsys.capella.common.re.ReDescriptionElement;
import org.polarsys.capella.common.re.ReElementContainer;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.ReNamedElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RePackageImpl extends EPackageImpl implements RePackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass reAbstractElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass reNamedElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass reDescriptionElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass reElementContainerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass recCatalogEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass groupingElementPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass catalogElementPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass catalogElementLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass catalogElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass compliancyDefinitionPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass compliancyDefinitionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum catalogElementKindEEnum = null;

	/**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.polarsys.capella.common.re.RePackage#eNS_URI
   * @see #init()
   * @generated
   */
	private RePackageImpl() {
    super(eNS_URI, ReFactory.eINSTANCE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static boolean isInited = false;

	/**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link RePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static RePackage init() {
    if (isInited) return (RePackage)EPackage.Registry.INSTANCE.getEPackage(RePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredRePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    RePackageImpl theRePackage = registeredRePackage instanceof RePackageImpl ? (RePackageImpl)registeredRePackage : new RePackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EmdePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theRePackage.createPackageContents();

    // Initialize created meta-data
    theRePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theRePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(RePackage.eNS_URI, theRePackage);
    return theRePackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getReAbstractElement() {
    return reAbstractElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getReAbstractElement_Id() {
    return (EAttribute)reAbstractElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getReNamedElement() {
    return reNamedElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getReNamedElement_Name() {
    return (EAttribute)reNamedElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getReDescriptionElement() {
    return reDescriptionElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getReDescriptionElement_Description() {
    return (EAttribute)reDescriptionElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getReElementContainer() {
    return reElementContainerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getReElementContainer_OwnedElements() {
    return (EReference)reElementContainerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getRecCatalog() {
    return recCatalogEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getRecCatalog_OwnedCompliancyDefinitionPkg() {
    return (EReference)recCatalogEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getGroupingElementPkg() {
    return groupingElementPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCatalogElementPkg() {
    return catalogElementPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElementPkg_OwnedElementPkgs() {
    return (EReference)catalogElementPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCatalogElementLink() {
    return catalogElementLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElementLink_Source() {
    return (EReference)catalogElementLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElementLink_Target() {
    return (EReference)catalogElementLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElementLink_Origin() {
    return (EReference)catalogElementLinkEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElementLink_UnsynchronizedFeatures() {
    return (EAttribute)catalogElementLinkEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElementLink_Suffixed() {
    return (EAttribute)catalogElementLinkEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCatalogElement() {
    return catalogElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_Kind() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_Author() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_Environment() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
		public EAttribute getCatalogElement_Suffix() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_Purpose() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_ReadOnly() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_Version() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCatalogElement_Tags() {
    return (EAttribute)catalogElementEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElement_Origin() {
    return (EReference)catalogElementEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElement_CurrentCompliancy() {
    return (EReference)catalogElementEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElement_DefaultReplicaCompliancy() {
    return (EReference)catalogElementEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElement_OwnedLinks() {
    return (EReference)catalogElementEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElement_ReferencedElements() {
    return (EReference)catalogElementEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCatalogElement_ReplicatedElements() {
    return (EReference)catalogElementEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCompliancyDefinitionPkg() {
    return compliancyDefinitionPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCompliancyDefinitionPkg_OwnedDefinitions() {
    return (EReference)compliancyDefinitionPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCompliancyDefinition() {
    return compliancyDefinitionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getCatalogElementKind() {
    return catalogElementKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ReFactory getReFactory() {
    return (ReFactory)getEFactoryInstance();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isCreated = false;

	/**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    reAbstractElementEClass = createEClass(RE_ABSTRACT_ELEMENT);
    createEAttribute(reAbstractElementEClass, RE_ABSTRACT_ELEMENT__ID);

    reNamedElementEClass = createEClass(RE_NAMED_ELEMENT);
    createEAttribute(reNamedElementEClass, RE_NAMED_ELEMENT__NAME);

    reDescriptionElementEClass = createEClass(RE_DESCRIPTION_ELEMENT);
    createEAttribute(reDescriptionElementEClass, RE_DESCRIPTION_ELEMENT__DESCRIPTION);

    reElementContainerEClass = createEClass(RE_ELEMENT_CONTAINER);
    createEReference(reElementContainerEClass, RE_ELEMENT_CONTAINER__OWNED_ELEMENTS);

    catalogElementPkgEClass = createEClass(CATALOG_ELEMENT_PKG);
    createEReference(catalogElementPkgEClass, CATALOG_ELEMENT_PKG__OWNED_ELEMENT_PKGS);

    recCatalogEClass = createEClass(REC_CATALOG);
    createEReference(recCatalogEClass, REC_CATALOG__OWNED_COMPLIANCY_DEFINITION_PKG);

    groupingElementPkgEClass = createEClass(GROUPING_ELEMENT_PKG);

    catalogElementLinkEClass = createEClass(CATALOG_ELEMENT_LINK);
    createEReference(catalogElementLinkEClass, CATALOG_ELEMENT_LINK__SOURCE);
    createEReference(catalogElementLinkEClass, CATALOG_ELEMENT_LINK__TARGET);
    createEReference(catalogElementLinkEClass, CATALOG_ELEMENT_LINK__ORIGIN);
    createEAttribute(catalogElementLinkEClass, CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES);
    createEAttribute(catalogElementLinkEClass, CATALOG_ELEMENT_LINK__SUFFIXED);

    catalogElementEClass = createEClass(CATALOG_ELEMENT);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__KIND);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__AUTHOR);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__ENVIRONMENT);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__SUFFIX);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__PURPOSE);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__READ_ONLY);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__VERSION);
    createEAttribute(catalogElementEClass, CATALOG_ELEMENT__TAGS);
    createEReference(catalogElementEClass, CATALOG_ELEMENT__ORIGIN);
    createEReference(catalogElementEClass, CATALOG_ELEMENT__CURRENT_COMPLIANCY);
    createEReference(catalogElementEClass, CATALOG_ELEMENT__DEFAULT_REPLICA_COMPLIANCY);
    createEReference(catalogElementEClass, CATALOG_ELEMENT__OWNED_LINKS);
    createEReference(catalogElementEClass, CATALOG_ELEMENT__REFERENCED_ELEMENTS);
    createEReference(catalogElementEClass, CATALOG_ELEMENT__REPLICATED_ELEMENTS);

    compliancyDefinitionPkgEClass = createEClass(COMPLIANCY_DEFINITION_PKG);
    createEReference(compliancyDefinitionPkgEClass, COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS);

    compliancyDefinitionEClass = createEClass(COMPLIANCY_DEFINITION);

    // Create enums
    catalogElementKindEEnum = createEEnum(CATALOG_ELEMENT_KIND);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isInitialized = false;

	/**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    EmdePackage theEmdePackage = (EmdePackage)EPackage.Registry.INSTANCE.getEPackage(EmdePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    reAbstractElementEClass.getESuperTypes().add(theEmdePackage.getExtensibleElement());
    reNamedElementEClass.getESuperTypes().add(this.getReAbstractElement());
    reDescriptionElementEClass.getESuperTypes().add(this.getReNamedElement());
    catalogElementPkgEClass.getESuperTypes().add(this.getReNamedElement());
    catalogElementPkgEClass.getESuperTypes().add(this.getReElementContainer());
    recCatalogEClass.getESuperTypes().add(this.getCatalogElementPkg());
    recCatalogEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    groupingElementPkgEClass.getESuperTypes().add(this.getCatalogElementPkg());
    groupingElementPkgEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    catalogElementLinkEClass.getESuperTypes().add(this.getReAbstractElement());
    catalogElementEClass.getESuperTypes().add(this.getReDescriptionElement());
    catalogElementEClass.getESuperTypes().add(this.getReElementContainer());
    compliancyDefinitionPkgEClass.getESuperTypes().add(this.getReNamedElement());
    compliancyDefinitionEClass.getESuperTypes().add(this.getReDescriptionElement());

    // Initialize classes and features; add operations and parameters
    initEClass(reAbstractElementEClass, ReAbstractElement.class, "ReAbstractElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getReAbstractElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, ReAbstractElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(reNamedElementEClass, ReNamedElement.class, "ReNamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getReNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, ReNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(reDescriptionElementEClass, ReDescriptionElement.class, "ReDescriptionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getReDescriptionElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ReDescriptionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(reElementContainerEClass, ReElementContainer.class, "ReElementContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getReElementContainer_OwnedElements(), this.getCatalogElement(), null, "ownedElements", null, 0, -1, ReElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(catalogElementPkgEClass, CatalogElementPkg.class, "CatalogElementPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCatalogElementPkg_OwnedElementPkgs(), this.getCatalogElementPkg(), null, "ownedElementPkgs", null, 0, -1, CatalogElementPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(recCatalogEClass, RecCatalog.class, "RecCatalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getRecCatalog_OwnedCompliancyDefinitionPkg(), this.getCompliancyDefinitionPkg(), null, "ownedCompliancyDefinitionPkg", null, 0, 1, RecCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(groupingElementPkgEClass, GroupingElementPkg.class, "GroupingElementPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(catalogElementLinkEClass, CatalogElementLink.class, "CatalogElementLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCatalogElementLink_Source(), this.getCatalogElement(), null, "source", null, 0, 1, CatalogElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElementLink_Target(), ecorePackage.getEObject(), null, "target", null, 0, 1, CatalogElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElementLink_Origin(), this.getCatalogElementLink(), null, "origin", null, 0, 1, CatalogElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElementLink_UnsynchronizedFeatures(), ecorePackage.getEString(), "unsynchronizedFeatures", null, 0, -1, CatalogElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElementLink_Suffixed(), ecorePackage.getEBoolean(), "suffixed", null, 0, 1, CatalogElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(catalogElementEClass, CatalogElement.class, "CatalogElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getCatalogElement_Kind(), this.getCatalogElementKind(), "kind", "REC", 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getCatalogElement_Author(), ecorePackage.getEString(), "author", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElement_Environment(), ecorePackage.getEString(), "environment", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElement_Suffix(), ecorePackage.getEString(), "suffix", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElement_Purpose(), ecorePackage.getEString(), "purpose", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElement_ReadOnly(), ecorePackage.getEBoolean(), "readOnly", "false", 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getCatalogElement_Version(), ecorePackage.getEString(), "version", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCatalogElement_Tags(), ecorePackage.getEString(), "tags", null, 0, -1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElement_Origin(), this.getCatalogElement(), null, "origin", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElement_CurrentCompliancy(), this.getCompliancyDefinition(), null, "currentCompliancy", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElement_DefaultReplicaCompliancy(), this.getCompliancyDefinition(), null, "defaultReplicaCompliancy", null, 0, 1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElement_OwnedLinks(), this.getCatalogElementLink(), null, "ownedLinks", null, 0, -1, CatalogElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElement_ReferencedElements(), ecorePackage.getEObject(), null, "referencedElements", null, 0, -1, CatalogElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCatalogElement_ReplicatedElements(), this.getCatalogElement(), null, "replicatedElements", null, 0, -1, CatalogElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(compliancyDefinitionPkgEClass, CompliancyDefinitionPkg.class, "CompliancyDefinitionPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCompliancyDefinitionPkg_OwnedDefinitions(), this.getCompliancyDefinition(), null, "ownedDefinitions", null, 0, -1, CompliancyDefinitionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(compliancyDefinitionEClass, CompliancyDefinition.class, "CompliancyDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(catalogElementKindEEnum, CatalogElementKind.class, "CatalogElementKind"); //$NON-NLS-1$
    addEEnumLiteral(catalogElementKindEEnum, CatalogElementKind.REC);
    addEEnumLiteral(catalogElementKindEEnum, CatalogElementKind.RPL);
    addEEnumLiteral(catalogElementKindEEnum, CatalogElementKind.REC_RPL);
    addEEnumLiteral(catalogElementKindEEnum, CatalogElementKind.GROUPING);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/capella/semantic
    createSemanticAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/constraint
    createConstraintAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping
    createConstraintMappingAnnotations();
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
    // http://www.polarsys.org/kitalpha/ecore/documentation
    createDocumentationAnnotations();
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/extension</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createExtensionAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/extension"; //$NON-NLS-1$
    addAnnotation
      (this,
       source,
       new String[] {
         "extensibleProviderFactory", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "childCreationExtenders", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/semantic</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createSemanticAnnotations() {
    String source = "http://www.polarsys.org/capella/semantic"; //$NON-NLS-1$
    addAnnotation
      (getReNamedElement_Name(),
       source,
       new String[] {
       });
    addAnnotation
      (getReDescriptionElement_Description(),
       source,
       new String[] {
       });
    addAnnotation
      (getReElementContainer_OwnedElements(),
       source,
       new String[] {
       });
    addAnnotation
      (catalogElementPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (catalogElementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Author(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Environment(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Suffix(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Purpose(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_ReadOnly(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Version(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Tags(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_Origin(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_ReferencedElements(),
       source,
       new String[] {
       });
    addAnnotation
      (getCatalogElement_ReplicatedElements(),
       source,
       new String[] {
       });
    addAnnotation
      (compliancyDefinitionEClass,
       source,
       new String[] {
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/constraint</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createConstraintAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/constraint"; //$NON-NLS-1$
    addAnnotation
      (recCatalogEClass,
       source,
       new String[] {
         "ExtendedElement", "http://www.polarsys.org/capella/core/modeller/7.0.0#//SystemEngineering " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (groupingElementPkgEClass,
       source,
       new String[] {
         "ExtendedElement", "http://www.polarsys.org/capella/core/modeller/7.0.0#//SystemEngineering http://www.polarsys.org/capella/core/cs/7.0.0#//BlockArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createConstraintMappingAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping"; //$NON-NLS-1$
    addAnnotation
      (recCatalogEClass,
       source,
       new String[] {
         "Mapping", "platform:/plugin/org.polarsys.capella.core.data.gen/model/CapellaModeller.ecore#//SystemEngineering " //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/derived</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createDerivedAnnotations() {
    String source = "http://www.polarsys.org/capella/derived"; //$NON-NLS-1$
    addAnnotation
      (getCatalogElement_ReferencedElements(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedLinks.target" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCatalogElement_ReplicatedElements(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "origin" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/ecore/documentation</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createDocumentationAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/ecore/documentation"; //$NON-NLS-1$
    addAnnotation
      (getCatalogElement_ReplicatedElements(),
       source,
       new String[] {
         "description", "retrieve all referencing elements which have the current element as origin" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //RePackageImpl
