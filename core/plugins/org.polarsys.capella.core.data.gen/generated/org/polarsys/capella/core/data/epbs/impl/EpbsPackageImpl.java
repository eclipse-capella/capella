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
package org.polarsys.capella.core.data.epbs.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.impl.CsPackageImpl;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
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
public class EpbsPackageImpl extends EPackageImpl implements EpbsPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass epbsArchitecturePkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass epbsArchitectureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass configurationItemPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass configurationItemEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalArchitectureRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalArtifactRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum configurationItemKindEEnum = null;

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
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private EpbsPackageImpl() {
    super(eNS_URI, EpbsFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link EpbsPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static EpbsPackage init() {
    if (isInited) return (EpbsPackage)EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredEpbsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    EpbsPackageImpl theEpbsPackage = registeredEpbsPackage instanceof EpbsPackageImpl ? (EpbsPackageImpl)registeredEpbsPackage : new EpbsPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    ModellingcorePackage.eINSTANCE.eClass();
    EmdePackage.eINSTANCE.eClass();
    ActivityPackage.eINSTANCE.eClass();
    BehaviorPackage.eINSTANCE.eClass();

    // Obtain or create and register interdependencies
    Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI);
    CapellamodellerPackageImpl theCapellamodellerPackage = (CapellamodellerPackageImpl)(registeredPackage instanceof CapellamodellerPackageImpl ? registeredPackage : CapellamodellerPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
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
    theEpbsPackage.createPackageContents();
    theCapellamodellerPackage.createPackageContents();
    theCapellacorePackage.createPackageContents();
    theOaPackage.createPackageContents();
    theCtxPackage.createPackageContents();
    theLaPackage.createPackageContents();
    thePaPackage.createPackageContents();
    theDeploymentPackage.createPackageContents();
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
    theEpbsPackage.initializePackageContents();
    theCapellamodellerPackage.initializePackageContents();
    theCapellacorePackage.initializePackageContents();
    theOaPackage.initializePackageContents();
    theCtxPackage.initializePackageContents();
    theLaPackage.initializePackageContents();
    thePaPackage.initializePackageContents();
    theDeploymentPackage.initializePackageContents();
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
    theEpbsPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(EpbsPackage.eNS_URI, theEpbsPackage);
    return theEpbsPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEPBSArchitecturePkg() {
    return epbsArchitecturePkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEPBSArchitecturePkg_OwnedEPBSArchitectures() {
    return (EReference)epbsArchitecturePkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEPBSArchitecture() {
    return epbsArchitectureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEPBSArchitecture_OwnedConfigurationItemPkg() {
    return (EReference)epbsArchitectureEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEPBSArchitecture_ContainedCapabilityRealizationPkg() {
    return (EReference)epbsArchitectureEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEPBSArchitecture_OwnedPhysicalArchitectureRealizations() {
    return (EReference)epbsArchitectureEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations() {
    return (EReference)epbsArchitectureEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEPBSArchitecture_AllocatedPhysicalArchitectures() {
    return (EReference)epbsArchitectureEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getConfigurationItemPkg() {
    return configurationItemPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConfigurationItemPkg_OwnedConfigurationItems() {
    return (EReference)configurationItemPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConfigurationItemPkg_OwnedConfigurationItemPkgs() {
    return (EReference)configurationItemPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getConfigurationItem() {
    return configurationItemEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getConfigurationItem_ItemIdentifier() {
    return (EAttribute)configurationItemEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getConfigurationItem_Kind() {
    return (EAttribute)configurationItemEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConfigurationItem_OwnedConfigurationItems() {
    return (EReference)configurationItemEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConfigurationItem_OwnedConfigurationItemPkgs() {
    return (EReference)configurationItemEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConfigurationItem_OwnedPhysicalArtifactRealizations() {
    return (EReference)configurationItemEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConfigurationItem_AllocatedPhysicalArtifacts() {
    return (EReference)configurationItemEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalArchitectureRealization() {
    return physicalArchitectureRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalArtifactRealization() {
    return physicalArtifactRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArtifactRealization_RealizedPhysicalArtifact() {
    return (EReference)physicalArtifactRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArtifactRealization_RealizingConfigurationItem() {
    return (EReference)physicalArtifactRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getConfigurationItemKind() {
    return configurationItemKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EpbsFactory getEpbsFactory() {
    return (EpbsFactory)getEFactoryInstance();
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
    epbsArchitecturePkgEClass = createEClass(EPBS_ARCHITECTURE_PKG);
    createEReference(epbsArchitecturePkgEClass, EPBS_ARCHITECTURE_PKG__OWNED_EPBS_ARCHITECTURES);

    epbsArchitectureEClass = createEClass(EPBS_ARCHITECTURE);
    createEReference(epbsArchitectureEClass, EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG);
    createEReference(epbsArchitectureEClass, EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG);
    createEReference(epbsArchitectureEClass, EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS);
    createEReference(epbsArchitectureEClass, EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS);
    createEReference(epbsArchitectureEClass, EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES);

    configurationItemPkgEClass = createEClass(CONFIGURATION_ITEM_PKG);
    createEReference(configurationItemPkgEClass, CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS);
    createEReference(configurationItemPkgEClass, CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS);

    configurationItemEClass = createEClass(CONFIGURATION_ITEM);
    createEAttribute(configurationItemEClass, CONFIGURATION_ITEM__ITEM_IDENTIFIER);
    createEAttribute(configurationItemEClass, CONFIGURATION_ITEM__KIND);
    createEReference(configurationItemEClass, CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEMS);
    createEReference(configurationItemEClass, CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEM_PKGS);
    createEReference(configurationItemEClass, CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);
    createEReference(configurationItemEClass, CONFIGURATION_ITEM__ALLOCATED_PHYSICAL_ARTIFACTS);

    physicalArchitectureRealizationEClass = createEClass(PHYSICAL_ARCHITECTURE_REALIZATION);

    physicalArtifactRealizationEClass = createEClass(PHYSICAL_ARTIFACT_REALIZATION);
    createEReference(physicalArtifactRealizationEClass, PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT);
    createEReference(physicalArtifactRealizationEClass, PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM);

    // Create enums
    configurationItemKindEEnum = createEEnum(CONFIGURATION_ITEM_KIND);
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
    CsPackage theCsPackage = (CsPackage)EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    LaPackage theLaPackage = (LaPackage)EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    PaPackage thePaPackage = (PaPackage)EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    CapellacorePackage theCapellacorePackage = (CapellacorePackage)EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    epbsArchitecturePkgEClass.getESuperTypes().add(theCsPackage.getBlockArchitecturePkg());
    epbsArchitectureEClass.getESuperTypes().add(theCsPackage.getComponentArchitecture());
    configurationItemPkgEClass.getESuperTypes().add(theCsPackage.getComponentPkg());
    configurationItemEClass.getESuperTypes().add(theCapellacommonPackage.getCapabilityRealizationInvolvedElement());
    configurationItemEClass.getESuperTypes().add(theCsPackage.getComponent());
    physicalArchitectureRealizationEClass.getESuperTypes().add(theCsPackage.getArchitectureAllocation());
    physicalArtifactRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());

    // Initialize classes and features; add operations and parameters
    initEClass(epbsArchitecturePkgEClass, EPBSArchitecturePkg.class, "EPBSArchitecturePkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEPBSArchitecturePkg_OwnedEPBSArchitectures(), this.getEPBSArchitecture(), null, "ownedEPBSArchitectures", null, 0, -1, EPBSArchitecturePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(epbsArchitectureEClass, EPBSArchitecture.class, "EPBSArchitecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEPBSArchitecture_OwnedConfigurationItemPkg(), this.getConfigurationItemPkg(), null, "ownedConfigurationItemPkg", null, 0, 1, EPBSArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEPBSArchitecture_ContainedCapabilityRealizationPkg(), theLaPackage.getCapabilityRealizationPkg(), null, "containedCapabilityRealizationPkg", null, 0, 1, EPBSArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEPBSArchitecture_OwnedPhysicalArchitectureRealizations(), this.getPhysicalArchitectureRealization(), null, "ownedPhysicalArchitectureRealizations", null, 0, -1, EPBSArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations(), this.getPhysicalArchitectureRealization(), null, "allocatedPhysicalArchitectureRealizations", null, 0, -1, EPBSArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEPBSArchitecture_AllocatedPhysicalArchitectures(), thePaPackage.getPhysicalArchitecture(), thePaPackage.getPhysicalArchitecture_AllocatingEpbsArchitectures(), "allocatedPhysicalArchitectures", null, 0, -1, EPBSArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(configurationItemPkgEClass, ConfigurationItemPkg.class, "ConfigurationItemPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getConfigurationItemPkg_OwnedConfigurationItems(), this.getConfigurationItem(), null, "ownedConfigurationItems", null, 0, -1, ConfigurationItemPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getConfigurationItemPkg_OwnedConfigurationItemPkgs(), this.getConfigurationItemPkg(), null, "ownedConfigurationItemPkgs", null, 0, -1, ConfigurationItemPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(configurationItemEClass, ConfigurationItem.class, "ConfigurationItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getConfigurationItem_ItemIdentifier(), ecorePackage.getEString(), "itemIdentifier", null, 0, 1, ConfigurationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getConfigurationItem_Kind(), this.getConfigurationItemKind(), "kind", "Unset", 0, 1, ConfigurationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getConfigurationItem_OwnedConfigurationItems(), this.getConfigurationItem(), null, "ownedConfigurationItems", null, 0, -1, ConfigurationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getConfigurationItem_OwnedConfigurationItemPkgs(), this.getConfigurationItemPkg(), null, "ownedConfigurationItemPkgs", null, 0, -1, ConfigurationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getConfigurationItem_OwnedPhysicalArtifactRealizations(), this.getPhysicalArtifactRealization(), null, "ownedPhysicalArtifactRealizations", null, 0, -1, ConfigurationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getConfigurationItem_AllocatedPhysicalArtifacts(), theCsPackage.getAbstractPhysicalArtifact(), theCsPackage.getAbstractPhysicalArtifact_AllocatorConfigurationItems(), "allocatedPhysicalArtifacts", null, 0, -1, ConfigurationItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalArchitectureRealizationEClass, PhysicalArchitectureRealization.class, "PhysicalArchitectureRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(physicalArtifactRealizationEClass, PhysicalArtifactRealization.class, "PhysicalArtifactRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalArtifactRealization_RealizedPhysicalArtifact(), theCsPackage.getAbstractPhysicalArtifact(), null, "realizedPhysicalArtifact", null, 1, 1, PhysicalArtifactRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArtifactRealization_RealizingConfigurationItem(), this.getConfigurationItem(), null, "realizingConfigurationItem", null, 1, 1, PhysicalArtifactRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(configurationItemKindEEnum, ConfigurationItemKind.class, "ConfigurationItemKind"); //$NON-NLS-1$
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.UNSET);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.COTSCI);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.CSCI);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.HWCI);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.INTERFACE_CI);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.NDICI);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.PRIME_ITEM_CI);
    addEEnumLiteral(configurationItemKindEEnum, ConfigurationItemKind.SYSTEM_CI);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/kitalpha/ecore/documentation
    createDocumentationAnnotations();
    // http://www.polarsys.org/capella/semantic
    createSemanticAnnotations();
    // http://www.polarsys.org/capella/2007/BusinessInformation
    createBusinessInformationAnnotations();
    // http://www.polarsys.org/capella/2007/UML2Mapping
    createUML2MappingAnnotations();
    // http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping
    createMappingAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
    createSegmentAnnotations();
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
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
         "description", "(E)PBS (for (End-)Product Breakdown Structure) aims at defining the system\'s work product breakdown (close to Clearcase/UCM\'s components concept).\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CompositeStructure.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (epbsArchitecturePkgEClass,
       source,
       new String[] {
         "description", "Package that contains end product breakdown structure architectures\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecturePkg_OwnedEPBSArchitectures(),
       source,
       new String[] {
         "description", "End product breakdown structure architectures set\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (epbsArchitectureEClass,
       source,
       new String[] {
         "description", "End Product Breakdown Structure. Definition of the Physical Components grouping for development subcontracting or purchase. ", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_OwnedConfigurationItemPkg(),
       source,
       new String[] {
         "description", "Set of packages that contain configuration items, owned by this EPBS architecture\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_OwnedPhysicalArchitectureRealizations(),
       source,
       new String[] {
         "description", "Set of physical architecture realization links owned by this EPBS architecture\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations(),
       source,
       new String[] {
         "description", "(automatically computed) the physical architecture realization links involving this EPBS architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemPkgEClass,
       source,
       new String[] {
         "description", "Package that contains configuration item elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this element is provided as a utility to better structure configuration items, if needed", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItems(),
       source,
       new String[] {
         "description", "Set of configuration items that are stored in the package\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "description", "Set of owned packages containing configuration items\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemEClass,
       source,
       new String[] {
         "description", "Aggregation of hardware, software, processed materials, services, or any of their discrete portions designated for configuration management and treated as a single entity in the configuration management process.", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "A configuration item is an abstract concept. Concrete concepts are : COTCI, CSCI, HWCI, InterfaceCI, NDICI, PrimeItemCI and SystemCI", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "arcadia_description", "A configuration item (CI) is a part of the system, to be \r\n- Designed and produced, or purchased\r\n- Duplicated as much as it is used in the system\r\n- assembled with others \r\nin order to build each copy of the system. \r\nExamples of configuration items are cabinets, racks, electronic boards, wiring & plugs, software components...\r\nCI are usually qualified as Hardware (HWCI), Computer Software (CSCI), Commercial off the Shelf (COTS, purchased item), Prime Item...\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItems(),
       source,
       new String[] {
         "description", "the children of this ConfigurationItem \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "description", "the sub-(configuration item) packages owned by this component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedPhysicalArtifactRealizations(),
       source,
       new String[] {
         "description", "Physical Artifact Realization links owned by this Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_AllocatedPhysicalArtifacts(),
       source,
       new String[] {
         "description", "(automatically computed) the list of realizations links coming from physical artifacts, and in which this ConfigurationItem is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Commercial Off The Shelves Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "Computer Software Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "Hardware Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "Interface Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "Non Developmental Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "Prime Item Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "description", "System Configuration Item", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitectureRealizationEClass,
       source,
       new String[] {
         "description", "Realization link betwen an EPBS architecture and a physical architecture\r\n[source:Capella study]\r\n\r\nRealization is a specialized abstraction relationship between two sets of model elements, one representing a specification\r\n(the supplier) and the other represents an implementation of the latter (the client). Realization can be used to model\r\nstepwise refinement, optimizations, transformations, templates, model synthesis, framework composition, etc.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArtifactRealizationEClass,
       source,
       new String[] {
         "description", "Realization link betwen a Configuration Item and a physical artifact", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArtifactRealization_RealizedPhysicalArtifact(),
       source,
       new String[] {
         "description", "Specifies the allocated architecture\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArtifactRealization_RealizingConfigurationItem(),
       source,
       new String[] {
         "description", "Specifies the allocating architecture\r\n[source: Capella study]\r\n\r\nSpecifies the sources of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
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
      (epbsArchitectureEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getEPBSArchitecture_OwnedConfigurationItemPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getEPBSArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "feature", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_AllocatedPhysicalArchitectures(),
       source,
       new String[] {
       });
    addAnnotation
      (configurationItemPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (configurationItemEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_ItemIdentifier(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_OwnedPhysicalArtifactRealizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_AllocatedPhysicalArtifacts(),
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
         "extensibleProviderFactory", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "childCreationExtenders", "true" //$NON-NLS-1$ //$NON-NLS-2$
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
      (epbsArchitecturePkgEClass,
       source,
       new String[] {
         "Label", "EPBSArchitecturePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecturePkg_OwnedEPBSArchitectures(),
       source,
       new String[] {
         "Label", "ownedEPBSArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (epbsArchitectureEClass,
       source,
       new String[] {
         "Label", "EPBSArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_OwnedConfigurationItemPkg(),
       source,
       new String[] {
         "Label", "ownedConfigurationItemPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemPkgEClass,
       source,
       new String[] {
         "Label", "ConfigurationItemPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItems(),
       source,
       new String[] {
         "Label", "ownedConfigurationItems" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "Label", "ownedConfigurationItemPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemEClass,
       source,
       new String[] {
         "Label", "ConfigurationItem" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItems(),
       source,
       new String[] {
         "Label", "ownedConfigurationItems" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "Label", "ownedConfigurationItemPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
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
      (epbsArchitecturePkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.sys.EPBSArchitecturePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecturePkg_OwnedEPBSArchitectures(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (epbsArchitectureEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.sys.EPBSArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_OwnedConfigurationItemPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.sys.ConfigurationItemPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItems(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemEClass,
       source,
       new String[] {
         "metaclass", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItems(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
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
      (epbsArchitecturePkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecturePkg_OwnedEPBSArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which EPBSArchitecture stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (epbsArchitectureEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_OwnedConfigurationItemPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which ConfigurationItemPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_OwnedPhysicalArchitectureRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which PhysicalArchitectureRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_AllocatedPhysicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ConfigurationItem stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which ConfigurationItemPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (configurationItemEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Blocks::Block", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Could have been mapped to Package (to be closer to the semantic of a \"group of\" physical components, \r\nbut it is not possible since there are Parts associated to CI\'s, and packages do not inherit from Type, hence cannot be used to type a Part.", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Class::nestedClassifier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Class::nestedClassifier elements on which ConfigurationItem stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "store them in the nearest possible package, since a Block cannot contain packages" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_AllocatedPhysicalArtifacts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitectureRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArtifactRealization_RealizingConfigurationItem(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getEPBSArchitecturePkg_OwnedEPBSArchitectures(),
       source,
       new String[] {
       });
    addAnnotation
      (getEPBSArchitecture_OwnedConfigurationItemPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItemPkg_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getConfigurationItem_OwnedConfigurationItemPkgs(),
       source,
       new String[] {
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
      (getEPBSArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedArchitectureAllocations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEPBSArchitecture_AllocatedPhysicalArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConfigurationItem_AllocatedPhysicalArtifacts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalArtifactRealization.sourceElement(par, self);\r\nPhysicalArtifactRealization.targetElement(par, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArtifactRealization_RealizedPhysicalArtifact(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArtifactRealization_RealizingConfigurationItem(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //EpbsPackageImpl
