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
package org.polarsys.capella.common.libraries.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.LibraryAbstractElement;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.libraries.ModelVersion;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LibrariesPackageImpl extends EPackageImpl implements LibrariesPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass modelInformationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass libraryReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass modelVersionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass libraryAbstractElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum accessPolicyEEnum = null;

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
   * @see org.polarsys.capella.common.libraries.LibrariesPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private LibrariesPackageImpl() {
    super(eNS_URI, LibrariesFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link LibrariesPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static LibrariesPackage init() {
    if (isInited) return (LibrariesPackage)EPackage.Registry.INSTANCE.getEPackage(LibrariesPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredLibrariesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    LibrariesPackageImpl theLibrariesPackage = registeredLibrariesPackage instanceof LibrariesPackageImpl ? (LibrariesPackageImpl)registeredLibrariesPackage : new LibrariesPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EmdePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theLibrariesPackage.createPackageContents();

    // Initialize created meta-data
    theLibrariesPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theLibrariesPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(LibrariesPackage.eNS_URI, theLibrariesPackage);
    return theLibrariesPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getModelInformation() {
    return modelInformationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getModelInformation_OwnedReferences() {
    return (EReference)modelInformationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getModelInformation_Version() {
    return (EReference)modelInformationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLibraryReference() {
    return libraryReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLibraryReference_Library() {
    return (EReference)libraryReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getLibraryReference_AccessPolicy() {
    return (EAttribute)libraryReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLibraryReference_Version() {
    return (EReference)libraryReferenceEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getModelVersion() {
    return modelVersionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getModelVersion_MajorVersionNumber() {
    return (EAttribute)modelVersionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getModelVersion_MinorVersionNumber() {
    return (EAttribute)modelVersionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getModelVersion_LastModifiedFileStamp() {
    return (EAttribute)modelVersionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLibraryAbstractElement() {
    return libraryAbstractElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getLibraryAbstractElement_Id() {
    return (EAttribute)libraryAbstractElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getAccessPolicy() {
    return accessPolicyEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public LibrariesFactory getLibrariesFactory() {
    return (LibrariesFactory)getEFactoryInstance();
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
    modelInformationEClass = createEClass(MODEL_INFORMATION);
    createEReference(modelInformationEClass, MODEL_INFORMATION__OWNED_REFERENCES);
    createEReference(modelInformationEClass, MODEL_INFORMATION__VERSION);

    libraryReferenceEClass = createEClass(LIBRARY_REFERENCE);
    createEReference(libraryReferenceEClass, LIBRARY_REFERENCE__LIBRARY);
    createEAttribute(libraryReferenceEClass, LIBRARY_REFERENCE__ACCESS_POLICY);
    createEReference(libraryReferenceEClass, LIBRARY_REFERENCE__VERSION);

    modelVersionEClass = createEClass(MODEL_VERSION);
    createEAttribute(modelVersionEClass, MODEL_VERSION__MAJOR_VERSION_NUMBER);
    createEAttribute(modelVersionEClass, MODEL_VERSION__MINOR_VERSION_NUMBER);
    createEAttribute(modelVersionEClass, MODEL_VERSION__LAST_MODIFIED_FILE_STAMP);

    libraryAbstractElementEClass = createEClass(LIBRARY_ABSTRACT_ELEMENT);
    createEAttribute(libraryAbstractElementEClass, LIBRARY_ABSTRACT_ELEMENT__ID);

    // Create enums
    accessPolicyEEnum = createEEnum(ACCESS_POLICY);
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
    modelInformationEClass.getESuperTypes().add(this.getLibraryAbstractElement());
    modelInformationEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    libraryReferenceEClass.getESuperTypes().add(this.getLibraryAbstractElement());
    modelVersionEClass.getESuperTypes().add(this.getLibraryAbstractElement());
    libraryAbstractElementEClass.getESuperTypes().add(theEmdePackage.getExtensibleElement());

    // Initialize classes and features; add operations and parameters
    initEClass(modelInformationEClass, ModelInformation.class, "ModelInformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getModelInformation_OwnedReferences(), this.getLibraryReference(), null, "ownedReferences", null, 0, -1, ModelInformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getModelInformation_Version(), this.getModelVersion(), null, "version", null, 0, 1, ModelInformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(libraryReferenceEClass, LibraryReference.class, "LibraryReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLibraryReference_Library(), this.getModelInformation(), null, "library", null, 1, 1, LibraryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getLibraryReference_AccessPolicy(), this.getAccessPolicy(), "accessPolicy", null, 1, 1, LibraryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLibraryReference_Version(), this.getModelVersion(), null, "version", null, 0, 1, LibraryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(modelVersionEClass, ModelVersion.class, "ModelVersion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getModelVersion_MajorVersionNumber(), ecorePackage.getEInt(), "majorVersionNumber", null, 1, 1, ModelVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getModelVersion_MinorVersionNumber(), ecorePackage.getEInt(), "minorVersionNumber", null, 1, 1, ModelVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getModelVersion_LastModifiedFileStamp(), ecorePackage.getELong(), "lastModifiedFileStamp", null, 0, 1, ModelVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(libraryAbstractElementEClass, LibraryAbstractElement.class, "LibraryAbstractElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getLibraryAbstractElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, LibraryAbstractElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(accessPolicyEEnum, AccessPolicy.class, "AccessPolicy"); //$NON-NLS-1$
    addEEnumLiteral(accessPolicyEEnum, AccessPolicy.READ_ONLY);
    addEEnumLiteral(accessPolicyEEnum, AccessPolicy.READ_AND_WRITE);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/constraint
    createConstraintAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping
    createConstraintMappingAnnotations();
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
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/constraint</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createConstraintAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/constraint"; //$NON-NLS-1$
    addAnnotation
      (modelInformationEClass,
       source,
       new String[] {
         "ExtendedElement", "http://www.polarsys.org/capella/core/modeller/7.0.0#//Project" //$NON-NLS-1$ //$NON-NLS-2$
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
      (modelInformationEClass,
       source,
       new String[] {
         "Mapping", "platform:/plugin/org.polarsys.capella.core.data.gen/model/CapellaModeller.ecore#//Project" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //LibrariesPackageImpl
