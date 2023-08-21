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
package org.polarsys.capella.core.data.capellamodeller.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Folder;
import org.polarsys.capella.core.data.capellamodeller.Library;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.impl.CsPackageImpl;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.impl.FaPackageImpl;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl;
import org.polarsys.capella.core.data.information.impl.InformationPackageImpl;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.impl.LaPackageImpl;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.impl.OaPackageImpl;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl;
import org.polarsys.capella.core.data.pa.impl.PaPackageImpl;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CapellamodellerPackageImpl extends EPackageImpl implements CapellamodellerPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass projectEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass folderEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass modelRootEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemEngineeringEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemEngineeringPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass libraryEClass = null;

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
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private CapellamodellerPackageImpl() {
    super(eNS_URI, CapellamodellerFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link CapellamodellerPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static CapellamodellerPackage init() {
    if (isInited) return (CapellamodellerPackage)EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredCapellamodellerPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    CapellamodellerPackageImpl theCapellamodellerPackage = registeredCapellamodellerPackage instanceof CapellamodellerPackageImpl ? (CapellamodellerPackageImpl)registeredCapellamodellerPackage : new CapellamodellerPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    ModellingcorePackage.eINSTANCE.eClass();
    EmdePackage.eINSTANCE.eClass();
    ActivityPackage.eINSTANCE.eClass();
    BehaviorPackage.eINSTANCE.eClass();

    // Obtain or create and register interdependencies
    Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
    CapellacorePackageImpl theCapellacorePackage = (CapellacorePackageImpl)(registeredPackage instanceof CapellacorePackageImpl ? registeredPackage : CapellacorePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI);
    OaPackageImpl theOaPackage = (OaPackageImpl)(registeredPackage instanceof OaPackageImpl ? registeredPackage : OaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI);
    CtxPackageImpl theCtxPackage = (CtxPackageImpl)(registeredPackage instanceof CtxPackageImpl ? registeredPackage : CtxPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    LaPackageImpl theLaPackage = (LaPackageImpl)(registeredPackage instanceof LaPackageImpl ? registeredPackage : LaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI);
    PaPackageImpl thePaPackage = (PaPackageImpl)(registeredPackage instanceof PaPackageImpl ? registeredPackage : PaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);
    DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(registeredPackage instanceof DeploymentPackageImpl ? registeredPackage : DeploymentPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI);
    EpbsPackageImpl theEpbsPackage = (EpbsPackageImpl)(registeredPackage instanceof EpbsPackageImpl ? registeredPackage : EpbsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI);
    SharedmodelPackageImpl theSharedmodelPackage = (SharedmodelPackageImpl)(registeredPackage instanceof SharedmodelPackageImpl ? registeredPackage : SharedmodelPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    CapellacommonPackageImpl theCapellacommonPackage = (CapellacommonPackageImpl)(registeredPackage instanceof CapellacommonPackageImpl ? registeredPackage : CapellacommonPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);
    InformationPackageImpl theInformationPackage = (InformationPackageImpl)(registeredPackage instanceof InformationPackageImpl ? registeredPackage : InformationPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI);
    CommunicationPackageImpl theCommunicationPackage = (CommunicationPackageImpl)(registeredPackage instanceof CommunicationPackageImpl ? registeredPackage : CommunicationPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);
    DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(registeredPackage instanceof DatatypePackageImpl ? registeredPackage : DatatypePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);
    DatavaluePackageImpl theDatavaluePackage = (DatavaluePackageImpl)(registeredPackage instanceof DatavaluePackageImpl ? registeredPackage : DatavaluePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    CsPackageImpl theCsPackage = (CsPackageImpl)(registeredPackage instanceof CsPackageImpl ? registeredPackage : CsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    FaPackageImpl theFaPackage = (FaPackageImpl)(registeredPackage instanceof FaPackageImpl ? registeredPackage : FaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theCapellamodellerPackage.createPackageContents();
    theCapellacorePackage.createPackageContents();
    theOaPackage.createPackageContents();
    theCtxPackage.createPackageContents();
    theLaPackage.createPackageContents();
    thePaPackage.createPackageContents();
    theDeploymentPackage.createPackageContents();
    theEpbsPackage.createPackageContents();
    theSharedmodelPackage.createPackageContents();
    theCapellacommonPackage.createPackageContents();
    theInformationPackage.createPackageContents();
    theCommunicationPackage.createPackageContents();
    theDatatypePackage.createPackageContents();
    theDatavaluePackage.createPackageContents();
    theCsPackage.createPackageContents();
    theFaPackage.createPackageContents();
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theCapellamodellerPackage.initializePackageContents();
    theCapellacorePackage.initializePackageContents();
    theOaPackage.initializePackageContents();
    theCtxPackage.initializePackageContents();
    theLaPackage.initializePackageContents();
    thePaPackage.initializePackageContents();
    theDeploymentPackage.initializePackageContents();
    theEpbsPackage.initializePackageContents();
    theSharedmodelPackage.initializePackageContents();
    theCapellacommonPackage.initializePackageContents();
    theInformationPackage.initializePackageContents();
    theCommunicationPackage.initializePackageContents();
    theDatatypePackage.initializePackageContents();
    theDatavaluePackage.initializePackageContents();
    theCsPackage.initializePackageContents();
    theFaPackage.initializePackageContents();
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theCapellamodellerPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(CapellamodellerPackage.eNS_URI, theCapellamodellerPackage);
    return theCapellamodellerPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getProject() {
    return projectEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getProject_KeyValuePairs() {
    return (EReference)projectEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getProject_OwnedFolders() {
    return (EReference)projectEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getProject_OwnedModelRoots() {
    return (EReference)projectEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFolder() {
    return folderEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFolder_OwnedFolders() {
    return (EReference)folderEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFolder_OwnedModelRoots() {
    return (EReference)folderEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getModelRoot() {
    return modelRootEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemEngineering() {
    return systemEngineeringEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineering_ContainedOperationalAnalysis() {
    return (EReference)systemEngineeringEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineering_ContainedSystemAnalysis() {
    return (EReference)systemEngineeringEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineering_ContainedLogicalArchitectures() {
    return (EReference)systemEngineeringEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineering_ContainedPhysicalArchitectures() {
    return (EReference)systemEngineeringEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineering_ContainedEPBSArchitectures() {
    return (EReference)systemEngineeringEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineering_ContainedSharedPkgs() {
    return (EReference)systemEngineeringEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemEngineeringPkg() {
    return systemEngineeringPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemEngineeringPkg_OwnedSystemEngineerings() {
    return (EReference)systemEngineeringPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLibrary() {
    return libraryEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapellamodellerFactory getCapellamodellerFactory() {
    return (CapellamodellerFactory)getEFactoryInstance();
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
    projectEClass = createEClass(PROJECT);
    createEReference(projectEClass, PROJECT__KEY_VALUE_PAIRS);
    createEReference(projectEClass, PROJECT__OWNED_FOLDERS);
    createEReference(projectEClass, PROJECT__OWNED_MODEL_ROOTS);

    folderEClass = createEClass(FOLDER);
    createEReference(folderEClass, FOLDER__OWNED_FOLDERS);
    createEReference(folderEClass, FOLDER__OWNED_MODEL_ROOTS);

    modelRootEClass = createEClass(MODEL_ROOT);

    systemEngineeringEClass = createEClass(SYSTEM_ENGINEERING);
    createEReference(systemEngineeringEClass, SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS);
    createEReference(systemEngineeringEClass, SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS);
    createEReference(systemEngineeringEClass, SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES);
    createEReference(systemEngineeringEClass, SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES);
    createEReference(systemEngineeringEClass, SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES);
    createEReference(systemEngineeringEClass, SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS);

    systemEngineeringPkgEClass = createEClass(SYSTEM_ENGINEERING_PKG);
    createEReference(systemEngineeringPkgEClass, SYSTEM_ENGINEERING_PKG__OWNED_SYSTEM_ENGINEERINGS);

    libraryEClass = createEClass(LIBRARY);
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
    CapellacorePackage theCapellacorePackage = (CapellacorePackage)EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
    OaPackage theOaPackage = (OaPackage)EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI);
    CtxPackage theCtxPackage = (CtxPackage)EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI);
    LaPackage theLaPackage = (LaPackage)EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    PaPackage thePaPackage = (PaPackage)EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI);
    EpbsPackage theEpbsPackage = (EpbsPackage)EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI);
    SharedmodelPackage theSharedmodelPackage = (SharedmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    projectEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    folderEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    modelRootEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    systemEngineeringEClass.getESuperTypes().add(theCapellacorePackage.getAbstractModellingStructure());
    systemEngineeringEClass.getESuperTypes().add(this.getModelRoot());
    systemEngineeringPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    systemEngineeringPkgEClass.getESuperTypes().add(this.getModelRoot());
    libraryEClass.getESuperTypes().add(this.getProject());

    // Initialize classes and features; add operations and parameters
    initEClass(projectEClass, Project.class, "Project", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getProject_KeyValuePairs(), theCapellacorePackage.getKeyValue(), null, "keyValuePairs", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getProject_OwnedFolders(), this.getFolder(), null, "ownedFolders", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getProject_OwnedModelRoots(), this.getModelRoot(), null, "ownedModelRoots", null, 0, -1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(folderEClass, Folder.class, "Folder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFolder_OwnedFolders(), this.getFolder(), null, "ownedFolders", null, 0, -1, Folder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFolder_OwnedModelRoots(), this.getModelRoot(), null, "ownedModelRoots", null, 0, -1, Folder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(modelRootEClass, ModelRoot.class, "ModelRoot", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(systemEngineeringEClass, SystemEngineering.class, "SystemEngineering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemEngineering_ContainedOperationalAnalysis(), theOaPackage.getOperationalAnalysis(), null, "containedOperationalAnalysis", null, 0, -1, SystemEngineering.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemEngineering_ContainedSystemAnalysis(), theCtxPackage.getSystemAnalysis(), null, "containedSystemAnalysis", null, 0, -1, SystemEngineering.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemEngineering_ContainedLogicalArchitectures(), theLaPackage.getLogicalArchitecture(), null, "containedLogicalArchitectures", null, 0, -1, SystemEngineering.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemEngineering_ContainedPhysicalArchitectures(), thePaPackage.getPhysicalArchitecture(), null, "containedPhysicalArchitectures", null, 0, -1, SystemEngineering.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemEngineering_ContainedEPBSArchitectures(), theEpbsPackage.getEPBSArchitecture(), null, "containedEPBSArchitectures", null, 0, -1, SystemEngineering.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemEngineering_ContainedSharedPkgs(), theSharedmodelPackage.getSharedPkg(), null, "containedSharedPkgs", null, 0, -1, SystemEngineering.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemEngineeringPkgEClass, SystemEngineeringPkg.class, "SystemEngineeringPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemEngineeringPkg_OwnedSystemEngineerings(), this.getSystemEngineering(), null, "ownedSystemEngineerings", null, 0, -1, SystemEngineeringPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/capella/2007/UML2Mapping
    createUML2MappingAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/kitalpha/ecore/documentation
    createDocumentationAnnotations();
    // http://www.polarsys.org/capella/semantic
    createSemanticAnnotations();
    // http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping
    createMappingAnnotations();
    // http://www.polarsys.org/capella/2007/BusinessInformation
    createBusinessInformationAnnotations();
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
    createSegmentAnnotations();
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/UML2Mapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createUML2MappingAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/UML2Mapping"; //$NON-NLS-1$
    addAnnotation
      (this,
       source,
       new String[] {
         "profileName", "Capella" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (projectEClass,
       source,
       new String[] {
         "metaclass", "Model", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "core.Project" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_KeyValuePairs(),
       source,
       new String[] {
         "featureName", "ownedComment", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Element" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedFolders(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedModelRoots(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (folderEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "core.Folder" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFolder_OwnedFolders(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFolder_OwnedModelRoots(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (modelRootEClass,
       source,
       new String[] {
         "metaclass", "Element" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.SystemEngineering" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.SystemEngineeringPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineeringPkg_OwnedSystemEngineerings(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
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
      (this,
       source,
       new String[] {
         "description", "CapellaModeller aims at defining project level concepts.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CapellaCore.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (projectEClass,
       source,
       new String[] {
         "description", "Project is the model root of a Capella model\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "A Project is the model root of a Capella model", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical, epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "None", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "None", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "None", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "None" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_KeyValuePairs(),
       source,
       new String[] {
         "description", "a list of key/value pairs applying to this Project\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedFolders(),
       source,
       new String[] {
         "description", "Set of folders owned by the project\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedModelRoots(),
       source,
       new String[] {
         "description", "Set of system engineering elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (folderEClass,
       source,
       new String[] {
         "description", "a container for structuring the storage of models\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical, epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFolder_OwnedFolders(),
       source,
       new String[] {
         "description", "Sub folders of this folder\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFolder_OwnedModelRoots(),
       source,
       new String[] {
         "description", "Set of system engineering elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (modelRootEClass,
       source,
       new String[] {
         "description", "A model root may be a system engineering element or a package of system engineering elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical, epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringEClass,
       source,
       new String[] {
         "description", "System engineering is an interdisciplinary approach encompassing the entire technical effort to evolve and verify an integrated and life-cycle balanced set of system people, product, and process solutions that satisfy customer needs.\r\nSystems engineering encompasses:\r\n- the technical efforts related to the development, manufacturing, verification, deployment, operations,\r\nsupport, disposal of, and user training for, systems products and processes;\r\n- the definition and management of the system configuration;\r\n- the translation of the system definition into work breakdown structures;\r\n- and development of information for management decision making\r\n[source:MIL-STD 499B standard]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical, epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringPkgEClass,
       source,
       new String[] {
         "description", "Package that contains system engineering elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical, epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineeringPkg_OwnedSystemEngineerings(),
       source,
       new String[] {
         "description", "Set of system engineering elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
      (this,
       source,
       new String[] {
       });
    addAnnotation
      (projectEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getProject_KeyValuePairs(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedModelRoots(),
       source,
       new String[] {
       });
    addAnnotation
      (systemEngineeringEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSystemEngineering_ContainedOperationalAnalysis(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedSystemAnalysis(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedLogicalArchitectures(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedPhysicalArchitectures(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedEPBSArchitectures(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedSharedPkgs(),
       source,
       new String[] {
         "feature", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (libraryEClass,
       source,
       new String[] {
       });
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
         "trackResourceModification", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "useUUIDs", "false", //$NON-NLS-1$ //$NON-NLS-2$
         "useIDAttributes", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "extensibleProviderFactory", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createMappingAnnotations() {
    String source = "http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping"; //$NON-NLS-1$
    addAnnotation
      (projectEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_KeyValuePairs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Element::ownedComment", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Element::ownedComment elements on which KeyValue stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedFolders(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which Folder stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProject_OwnedModelRoots(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ModelRoot stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (folderEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFolder_OwnedFolders(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFolder_OwnedModelRoots(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ModelRoot stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (modelRootEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedOperationalAnalysis(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedSystemAnalysis(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedLogicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedPhysicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedEPBSArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedSharedPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineeringPkg_OwnedSystemEngineerings(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/BusinessInformation</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createBusinessInformationAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/BusinessInformation"; //$NON-NLS-1$
    addAnnotation
      (systemEngineeringEClass,
       source,
       new String[] {
         "Label", "System Engineering" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemEngineeringPkgEClass,
       source,
       new String[] {
         "Label", "SystemEngineeringPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineeringPkg_OwnedSystemEngineerings(),
       source,
       new String[] {
         "Label", "ownedSystemEngineerings" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getSystemEngineering_ContainedOperationalAnalysis(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedSystemAnalysis(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedLogicalArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedPhysicalArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedEPBSArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemEngineering_ContainedSharedPkgs(),
       source,
       new String[] {
         "viatra.variant", "unimplemented", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedArchitectures as SharedPkg, but SharedPkg is not a subclass of ModellingArchitecture !" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createSegmentAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"; //$NON-NLS-1$
    addAnnotation
      (getSystemEngineeringPkg_OwnedSystemEngineerings(),
       source,
       new String[] {
       });
  }

} //CapellamodellerPackageImpl
