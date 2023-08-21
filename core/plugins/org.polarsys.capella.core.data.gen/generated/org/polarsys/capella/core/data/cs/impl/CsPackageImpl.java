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
package org.polarsys.capella.core.data.cs.impl;

import org.eclipse.emf.ecore.EAttribute;
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
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecturePkg;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathRealization;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.cs.ProvidedInterfaceLink;
import org.polarsys.capella.core.data.cs.RequiredInterfaceLink;
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
public class CsPackageImpl extends EPackageImpl implements CsPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass blockArchitecturePkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass blockArchitectureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass blockEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentArchitectureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass partEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass architectureAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interfacePkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interfaceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interfaceImplementationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interfaceUseEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass providedInterfaceLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass requiredInterfaceLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interfaceAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interfaceAllocatorEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeItemAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass deployableElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass deploymentTargetEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractDeploymentLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractPathInvolvedElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractPhysicalArtifactEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractPhysicalLinkEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractPhysicalPathLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalLinkCategoryEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalLinkEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalLinkRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalPathEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalPathInvolvementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalPathReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalPathRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalPortEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalPortRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentPkgEClass = null;

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
   * @see org.polarsys.capella.core.data.cs.CsPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private CsPackageImpl() {
    super(eNS_URI, CsFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link CsPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static CsPackage init() {
    if (isInited) return (CsPackage)EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredCsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    CsPackageImpl theCsPackage = registeredCsPackage instanceof CsPackageImpl ? (CsPackageImpl)registeredCsPackage : new CsPackageImpl();

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
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    FaPackageImpl theFaPackage = (FaPackageImpl)(registeredPackage instanceof FaPackageImpl ? registeredPackage : FaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theCsPackage.createPackageContents();
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
    theFaPackage.createPackageContents();
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theCsPackage.initializePackageContents();
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
    theFaPackage.initializePackageContents();
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theCsPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(CsPackage.eNS_URI, theCsPackage);
    return theCsPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getBlockArchitecturePkg() {
    return blockArchitecturePkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getBlockArchitecture() {
    return blockArchitectureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_OwnedAbstractCapabilityPkg() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_OwnedInterfacePkg() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_OwnedDataPkg() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_ProvisionedArchitectureAllocations() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_ProvisioningArchitectureAllocations() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_AllocatedArchitectures() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_AllocatingArchitectures() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlockArchitecture_System() {
    return (EReference)blockArchitectureEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getBlock() {
    return blockEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlock_OwnedAbstractCapabilityPkg() {
    return (EReference)blockEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlock_OwnedInterfacePkg() {
    return (EReference)blockEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlock_OwnedDataPkg() {
    return (EReference)blockEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBlock_OwnedStateMachines() {
    return (EReference)blockEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentArchitecture() {
    return componentArchitectureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponent() {
    return componentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getComponent_Actor() {
    return (EAttribute)componentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getComponent_Human() {
    return (EAttribute)componentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_OwnedInterfaceUses() {
    return (EReference)componentEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_UsedInterfaceLinks() {
    return (EReference)componentEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_UsedInterfaces() {
    return (EReference)componentEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_OwnedInterfaceImplementations() {
    return (EReference)componentEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_ImplementedInterfaceLinks() {
    return (EReference)componentEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_ImplementedInterfaces() {
    return (EReference)componentEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_OwnedComponentRealizations() {
    return (EReference)componentEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_RealizedComponents() {
    return (EReference)componentEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_RealizingComponents() {
    return (EReference)componentEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_ProvidedInterfaces() {
    return (EReference)componentEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_RequiredInterfaces() {
    return (EReference)componentEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_ContainedComponentPorts() {
    return (EReference)componentEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_ContainedParts() {
    return (EReference)componentEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_ContainedPhysicalPorts() {
    return (EReference)componentEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_OwnedPhysicalPath() {
    return (EReference)componentEClass.getEStructuralFeatures().get(16);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_OwnedPhysicalLinks() {
    return (EReference)componentEClass.getEStructuralFeatures().get(17);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_OwnedPhysicalLinkCategories() {
    return (EReference)componentEClass.getEStructuralFeatures().get(18);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponent_RepresentingParts() {
    return (EReference)componentEClass.getEStructuralFeatures().get(19);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPart() {
    return partEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPart_ProvidedInterfaces() {
    return (EReference)partEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPart_RequiredInterfaces() {
    return (EReference)partEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPart_OwnedDeploymentLinks() {
    return (EReference)partEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPart_DeployedParts() {
    return (EReference)partEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPart_DeployingParts() {
    return (EReference)partEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPart_OwnedAbstractType() {
    return (EReference)partEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getArchitectureAllocation() {
    return architectureAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getArchitectureAllocation_AllocatedArchitecture() {
    return (EReference)architectureAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getArchitectureAllocation_AllocatingArchitecture() {
    return (EReference)architectureAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentRealization() {
    return componentRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentRealization_RealizedComponent() {
    return (EReference)componentRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentRealization_RealizingComponent() {
    return (EReference)componentRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInterfacePkg() {
    return interfacePkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfacePkg_OwnedInterfaces() {
    return (EReference)interfacePkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfacePkg_OwnedInterfacePkgs() {
    return (EReference)interfacePkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInterface() {
    return interfaceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getInterface_Mechanism() {
    return (EAttribute)interfaceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getInterface_Structural() {
    return (EAttribute)interfaceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_ImplementorComponents() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_UserComponents() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_InterfaceImplementations() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_InterfaceUses() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_ProvisioningInterfaceAllocations() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_AllocatingInterfaces() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_AllocatingComponents() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_ExchangeItems() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_OwnedExchangeItemAllocations() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_RequiringComponents() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_RequiringComponentPorts() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_ProvidingComponents() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_ProvidingComponentPorts() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_RealizingLogicalInterfaces() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_RealizedContextInterfaces() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(16);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_RealizingPhysicalInterfaces() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(17);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterface_RealizedLogicalInterfaces() {
    return (EReference)interfaceEClass.getEStructuralFeatures().get(18);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInterfaceImplementation() {
    return interfaceImplementationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceImplementation_InterfaceImplementor() {
    return (EReference)interfaceImplementationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceImplementation_ImplementedInterface() {
    return (EReference)interfaceImplementationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInterfaceUse() {
    return interfaceUseEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceUse_InterfaceUser() {
    return (EReference)interfaceUseEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceUse_UsedInterface() {
    return (EReference)interfaceUseEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getProvidedInterfaceLink() {
    return providedInterfaceLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getProvidedInterfaceLink_Interface() {
    return (EReference)providedInterfaceLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getRequiredInterfaceLink() {
    return requiredInterfaceLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getRequiredInterfaceLink_Interface() {
    return (EReference)requiredInterfaceLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInterfaceAllocation() {
    return interfaceAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceAllocation_AllocatedInterface() {
    return (EReference)interfaceAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceAllocation_AllocatingInterfaceAllocator() {
    return (EReference)interfaceAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInterfaceAllocator() {
    return interfaceAllocatorEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceAllocator_OwnedInterfaceAllocations() {
    return (EReference)interfaceAllocatorEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceAllocator_ProvisionedInterfaceAllocations() {
    return (EReference)interfaceAllocatorEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInterfaceAllocator_AllocatedInterfaces() {
    return (EReference)interfaceAllocatorEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeItemAllocation() {
    return exchangeItemAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getExchangeItemAllocation_SendProtocol() {
    return (EAttribute)exchangeItemAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getExchangeItemAllocation_ReceiveProtocol() {
    return (EAttribute)exchangeItemAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItemAllocation_AllocatedItem() {
    return (EReference)exchangeItemAllocationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItemAllocation_AllocatingInterface() {
    return (EReference)exchangeItemAllocationEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDeployableElement() {
    return deployableElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDeployableElement_DeployingLinks() {
    return (EReference)deployableElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDeploymentTarget() {
    return deploymentTargetEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDeploymentTarget_DeploymentLinks() {
    return (EReference)deploymentTargetEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractDeploymentLink() {
    return abstractDeploymentLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractDeploymentLink_DeployedElement() {
    return (EReference)abstractDeploymentLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractDeploymentLink_Location() {
    return (EReference)abstractDeploymentLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractPathInvolvedElement() {
    return abstractPathInvolvedElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractPhysicalArtifact() {
    return abstractPhysicalArtifactEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractPhysicalArtifact_AllocatorConfigurationItems() {
    return (EReference)abstractPhysicalArtifactEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractPhysicalLinkEnd() {
    return abstractPhysicalLinkEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractPhysicalLinkEnd_InvolvedLinks() {
    return (EReference)abstractPhysicalLinkEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractPhysicalPathLink() {
    return abstractPhysicalPathLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalLink() {
    return physicalLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_LinkEnds() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_OwnedComponentExchangeFunctionalExchangeAllocations() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_OwnedPhysicalLinkEnds() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_OwnedPhysicalLinkRealizations() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_Categories() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_SourcePhysicalPort() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_TargetPhysicalPort() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_RealizedPhysicalLinks() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLink_RealizingPhysicalLinks() {
    return (EReference)physicalLinkEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalLinkCategory() {
    return physicalLinkCategoryEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLinkCategory_Links() {
    return (EReference)physicalLinkCategoryEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalLinkEnd() {
    return physicalLinkEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLinkEnd_Port() {
    return (EReference)physicalLinkEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalLinkEnd_Part() {
    return (EReference)physicalLinkEndEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalLinkRealization() {
    return physicalLinkRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalPath() {
    return physicalPathEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks() model documentation} for details.
   * @generated
   */
	@Deprecated
		@Override
	public EReference getPhysicalPath_InvolvedLinks() {
    return (EReference)physicalPathEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPath_OwnedPhysicalPathInvolvements() {
    return (EReference)physicalPathEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPath_FirstPhysicalPathInvolvements() {
    return (EReference)physicalPathEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPath_OwnedPhysicalPathRealizations() {
    return (EReference)physicalPathEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPath_RealizedPhysicalPaths() {
    return (EReference)physicalPathEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPath_RealizingPhysicalPaths() {
    return (EReference)physicalPathEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalPathInvolvement() {
    return physicalPathInvolvementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPathInvolvement_NextInvolvements() {
    return (EReference)physicalPathInvolvementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPathInvolvement_PreviousInvolvements() {
    return (EReference)physicalPathInvolvementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPathInvolvement_InvolvedElement() {
    return (EReference)physicalPathInvolvementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPathInvolvement_InvolvedComponent() {
    return (EReference)physicalPathInvolvementEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalPathReference() {
    return physicalPathReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPathReference_ReferencedPhysicalPath() {
    return (EReference)physicalPathReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalPathRealization() {
    return physicalPathRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalPort() {
    return physicalPortEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPort_OwnedComponentPortAllocations() {
    return (EReference)physicalPortEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPort_OwnedPhysicalPortRealizations() {
    return (EReference)physicalPortEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPort_AllocatedComponentPorts() {
    return (EReference)physicalPortEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPort_RealizedPhysicalPorts() {
    return (EReference)physicalPortEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalPort_RealizingPhysicalPorts() {
    return (EReference)physicalPortEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalPortRealization() {
    return physicalPortRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentPkg() {
    return componentPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedParts() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedComponentExchanges() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedComponentExchangeCategories() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedFunctionalLinks() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedFunctionalAllocations() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedComponentExchangeRealizations() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedPhysicalLinks() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedPhysicalLinkCategories() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPkg_OwnedStateMachines() {
    return (EReference)componentPkgEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CsFactory getCsFactory() {
    return (CsFactory)getEFactoryInstance();
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
	@SuppressWarnings("deprecation")
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    blockArchitecturePkgEClass = createEClass(BLOCK_ARCHITECTURE_PKG);

    blockArchitectureEClass = createEClass(BLOCK_ARCHITECTURE);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__OWNED_DATA_PKG);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES);
    createEReference(blockArchitectureEClass, BLOCK_ARCHITECTURE__SYSTEM);

    blockEClass = createEClass(BLOCK);
    createEReference(blockEClass, BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG);
    createEReference(blockEClass, BLOCK__OWNED_INTERFACE_PKG);
    createEReference(blockEClass, BLOCK__OWNED_DATA_PKG);
    createEReference(blockEClass, BLOCK__OWNED_STATE_MACHINES);

    componentArchitectureEClass = createEClass(COMPONENT_ARCHITECTURE);

    componentEClass = createEClass(COMPONENT);
    createEAttribute(componentEClass, COMPONENT__ACTOR);
    createEAttribute(componentEClass, COMPONENT__HUMAN);
    createEReference(componentEClass, COMPONENT__OWNED_INTERFACE_USES);
    createEReference(componentEClass, COMPONENT__USED_INTERFACE_LINKS);
    createEReference(componentEClass, COMPONENT__USED_INTERFACES);
    createEReference(componentEClass, COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    createEReference(componentEClass, COMPONENT__IMPLEMENTED_INTERFACE_LINKS);
    createEReference(componentEClass, COMPONENT__IMPLEMENTED_INTERFACES);
    createEReference(componentEClass, COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    createEReference(componentEClass, COMPONENT__REALIZED_COMPONENTS);
    createEReference(componentEClass, COMPONENT__REALIZING_COMPONENTS);
    createEReference(componentEClass, COMPONENT__PROVIDED_INTERFACES);
    createEReference(componentEClass, COMPONENT__REQUIRED_INTERFACES);
    createEReference(componentEClass, COMPONENT__CONTAINED_COMPONENT_PORTS);
    createEReference(componentEClass, COMPONENT__CONTAINED_PARTS);
    createEReference(componentEClass, COMPONENT__CONTAINED_PHYSICAL_PORTS);
    createEReference(componentEClass, COMPONENT__OWNED_PHYSICAL_PATH);
    createEReference(componentEClass, COMPONENT__OWNED_PHYSICAL_LINKS);
    createEReference(componentEClass, COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES);
    createEReference(componentEClass, COMPONENT__REPRESENTING_PARTS);

    partEClass = createEClass(PART);
    createEReference(partEClass, PART__PROVIDED_INTERFACES);
    createEReference(partEClass, PART__REQUIRED_INTERFACES);
    createEReference(partEClass, PART__OWNED_DEPLOYMENT_LINKS);
    createEReference(partEClass, PART__DEPLOYED_PARTS);
    createEReference(partEClass, PART__DEPLOYING_PARTS);
    createEReference(partEClass, PART__OWNED_ABSTRACT_TYPE);

    architectureAllocationEClass = createEClass(ARCHITECTURE_ALLOCATION);
    createEReference(architectureAllocationEClass, ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE);
    createEReference(architectureAllocationEClass, ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE);

    componentRealizationEClass = createEClass(COMPONENT_REALIZATION);
    createEReference(componentRealizationEClass, COMPONENT_REALIZATION__REALIZED_COMPONENT);
    createEReference(componentRealizationEClass, COMPONENT_REALIZATION__REALIZING_COMPONENT);

    interfacePkgEClass = createEClass(INTERFACE_PKG);
    createEReference(interfacePkgEClass, INTERFACE_PKG__OWNED_INTERFACES);
    createEReference(interfacePkgEClass, INTERFACE_PKG__OWNED_INTERFACE_PKGS);

    interfaceEClass = createEClass(INTERFACE);
    createEAttribute(interfaceEClass, INTERFACE__MECHANISM);
    createEAttribute(interfaceEClass, INTERFACE__STRUCTURAL);
    createEReference(interfaceEClass, INTERFACE__IMPLEMENTOR_COMPONENTS);
    createEReference(interfaceEClass, INTERFACE__USER_COMPONENTS);
    createEReference(interfaceEClass, INTERFACE__INTERFACE_IMPLEMENTATIONS);
    createEReference(interfaceEClass, INTERFACE__INTERFACE_USES);
    createEReference(interfaceEClass, INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS);
    createEReference(interfaceEClass, INTERFACE__ALLOCATING_INTERFACES);
    createEReference(interfaceEClass, INTERFACE__ALLOCATING_COMPONENTS);
    createEReference(interfaceEClass, INTERFACE__EXCHANGE_ITEMS);
    createEReference(interfaceEClass, INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS);
    createEReference(interfaceEClass, INTERFACE__REQUIRING_COMPONENTS);
    createEReference(interfaceEClass, INTERFACE__REQUIRING_COMPONENT_PORTS);
    createEReference(interfaceEClass, INTERFACE__PROVIDING_COMPONENTS);
    createEReference(interfaceEClass, INTERFACE__PROVIDING_COMPONENT_PORTS);
    createEReference(interfaceEClass, INTERFACE__REALIZING_LOGICAL_INTERFACES);
    createEReference(interfaceEClass, INTERFACE__REALIZED_CONTEXT_INTERFACES);
    createEReference(interfaceEClass, INTERFACE__REALIZING_PHYSICAL_INTERFACES);
    createEReference(interfaceEClass, INTERFACE__REALIZED_LOGICAL_INTERFACES);

    interfaceImplementationEClass = createEClass(INTERFACE_IMPLEMENTATION);
    createEReference(interfaceImplementationEClass, INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR);
    createEReference(interfaceImplementationEClass, INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE);

    interfaceUseEClass = createEClass(INTERFACE_USE);
    createEReference(interfaceUseEClass, INTERFACE_USE__INTERFACE_USER);
    createEReference(interfaceUseEClass, INTERFACE_USE__USED_INTERFACE);

    providedInterfaceLinkEClass = createEClass(PROVIDED_INTERFACE_LINK);
    createEReference(providedInterfaceLinkEClass, PROVIDED_INTERFACE_LINK__INTERFACE);

    requiredInterfaceLinkEClass = createEClass(REQUIRED_INTERFACE_LINK);
    createEReference(requiredInterfaceLinkEClass, REQUIRED_INTERFACE_LINK__INTERFACE);

    interfaceAllocationEClass = createEClass(INTERFACE_ALLOCATION);
    createEReference(interfaceAllocationEClass, INTERFACE_ALLOCATION__ALLOCATED_INTERFACE);
    createEReference(interfaceAllocationEClass, INTERFACE_ALLOCATION__ALLOCATING_INTERFACE_ALLOCATOR);

    interfaceAllocatorEClass = createEClass(INTERFACE_ALLOCATOR);
    createEReference(interfaceAllocatorEClass, INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS);
    createEReference(interfaceAllocatorEClass, INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS);
    createEReference(interfaceAllocatorEClass, INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES);

    exchangeItemAllocationEClass = createEClass(EXCHANGE_ITEM_ALLOCATION);
    createEAttribute(exchangeItemAllocationEClass, EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL);
    createEAttribute(exchangeItemAllocationEClass, EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL);
    createEReference(exchangeItemAllocationEClass, EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
    createEReference(exchangeItemAllocationEClass, EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE);

    deployableElementEClass = createEClass(DEPLOYABLE_ELEMENT);
    createEReference(deployableElementEClass, DEPLOYABLE_ELEMENT__DEPLOYING_LINKS);

    deploymentTargetEClass = createEClass(DEPLOYMENT_TARGET);
    createEReference(deploymentTargetEClass, DEPLOYMENT_TARGET__DEPLOYMENT_LINKS);

    abstractDeploymentLinkEClass = createEClass(ABSTRACT_DEPLOYMENT_LINK);
    createEReference(abstractDeploymentLinkEClass, ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
    createEReference(abstractDeploymentLinkEClass, ABSTRACT_DEPLOYMENT_LINK__LOCATION);

    abstractPathInvolvedElementEClass = createEClass(ABSTRACT_PATH_INVOLVED_ELEMENT);

    abstractPhysicalArtifactEClass = createEClass(ABSTRACT_PHYSICAL_ARTIFACT);
    createEReference(abstractPhysicalArtifactEClass, ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS);

    abstractPhysicalLinkEndEClass = createEClass(ABSTRACT_PHYSICAL_LINK_END);
    createEReference(abstractPhysicalLinkEndEClass, ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS);

    abstractPhysicalPathLinkEClass = createEClass(ABSTRACT_PHYSICAL_PATH_LINK);

    physicalLinkEClass = createEClass(PHYSICAL_LINK);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__LINK_ENDS);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__CATEGORIES);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__SOURCE_PHYSICAL_PORT);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__TARGET_PHYSICAL_PORT);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS);
    createEReference(physicalLinkEClass, PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS);

    physicalLinkCategoryEClass = createEClass(PHYSICAL_LINK_CATEGORY);
    createEReference(physicalLinkCategoryEClass, PHYSICAL_LINK_CATEGORY__LINKS);

    physicalLinkEndEClass = createEClass(PHYSICAL_LINK_END);
    createEReference(physicalLinkEndEClass, PHYSICAL_LINK_END__PORT);
    createEReference(physicalLinkEndEClass, PHYSICAL_LINK_END__PART);

    physicalLinkRealizationEClass = createEClass(PHYSICAL_LINK_REALIZATION);

    physicalPathEClass = createEClass(PHYSICAL_PATH);
    createEReference(physicalPathEClass, PHYSICAL_PATH__INVOLVED_LINKS);
    createEReference(physicalPathEClass, PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS);
    createEReference(physicalPathEClass, PHYSICAL_PATH__FIRST_PHYSICAL_PATH_INVOLVEMENTS);
    createEReference(physicalPathEClass, PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS);
    createEReference(physicalPathEClass, PHYSICAL_PATH__REALIZED_PHYSICAL_PATHS);
    createEReference(physicalPathEClass, PHYSICAL_PATH__REALIZING_PHYSICAL_PATHS);

    physicalPathInvolvementEClass = createEClass(PHYSICAL_PATH_INVOLVEMENT);
    createEReference(physicalPathInvolvementEClass, PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS);
    createEReference(physicalPathInvolvementEClass, PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS);
    createEReference(physicalPathInvolvementEClass, PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT);
    createEReference(physicalPathInvolvementEClass, PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT);

    physicalPathReferenceEClass = createEClass(PHYSICAL_PATH_REFERENCE);
    createEReference(physicalPathReferenceEClass, PHYSICAL_PATH_REFERENCE__REFERENCED_PHYSICAL_PATH);

    physicalPathRealizationEClass = createEClass(PHYSICAL_PATH_REALIZATION);

    physicalPortEClass = createEClass(PHYSICAL_PORT);
    createEReference(physicalPortEClass, PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS);
    createEReference(physicalPortEClass, PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS);
    createEReference(physicalPortEClass, PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS);
    createEReference(physicalPortEClass, PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS);
    createEReference(physicalPortEClass, PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS);

    physicalPortRealizationEClass = createEClass(PHYSICAL_PORT_REALIZATION);

    componentPkgEClass = createEClass(COMPONENT_PKG);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_PARTS);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_PHYSICAL_LINKS);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES);
    createEReference(componentPkgEClass, COMPONENT_PKG__OWNED_STATE_MACHINES);
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
    FaPackage theFaPackage = (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);
    CommunicationPackage theCommunicationPackage = (CommunicationPackage)EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI);
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);
    EpbsPackage theEpbsPackage = (EpbsPackage)EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    blockArchitecturePkgEClass.getESuperTypes().add(theCapellacorePackage.getModellingArchitecturePkg());
    blockArchitectureEClass.getESuperTypes().add(theFaPackage.getAbstractFunctionalArchitecture());
    blockEClass.getESuperTypes().add(theCapellacorePackage.getModellingBlock());
    blockEClass.getESuperTypes().add(theFaPackage.getAbstractFunctionalBlock());
    componentArchitectureEClass.getESuperTypes().add(this.getBlockArchitecture());
    componentEClass.getESuperTypes().add(this.getBlock());
    componentEClass.getESuperTypes().add(theCapellacorePackage.getClassifier());
    componentEClass.getESuperTypes().add(this.getInterfaceAllocator());
    componentEClass.getESuperTypes().add(theCommunicationPackage.getCommunicationLinkExchanger());
    partEClass.getESuperTypes().add(theInformationPackage.getAbstractInstance());
    partEClass.getESuperTypes().add(theModellingcorePackage.getInformationsExchanger());
    partEClass.getESuperTypes().add(this.getDeployableElement());
    partEClass.getESuperTypes().add(this.getDeploymentTarget());
    partEClass.getESuperTypes().add(this.getAbstractPathInvolvedElement());
    architectureAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    componentRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    interfacePkgEClass.getESuperTypes().add(theCommunicationPackage.getMessageReferencePkg());
    interfacePkgEClass.getESuperTypes().add(theCapellacorePackage.getAbstractDependenciesPkg());
    interfacePkgEClass.getESuperTypes().add(theCapellacorePackage.getAbstractExchangeItemPkg());
    interfaceEClass.getESuperTypes().add(theCapellacorePackage.getGeneralClass());
    interfaceEClass.getESuperTypes().add(this.getInterfaceAllocator());
    interfaceImplementationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    interfaceUseEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    providedInterfaceLinkEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    requiredInterfaceLinkEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    interfaceAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    interfaceAllocatorEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    exchangeItemAllocationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    exchangeItemAllocationEClass.getESuperTypes().add(theInformationPackage.getAbstractEventOperation());
    exchangeItemAllocationEClass.getESuperTypes().add(theModellingcorePackage.getFinalizableElement());
    deployableElementEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    deploymentTargetEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    abstractDeploymentLinkEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    abstractPathInvolvedElementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvedElement());
    abstractPhysicalArtifactEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    abstractPhysicalLinkEndEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    abstractPhysicalPathLinkEClass.getESuperTypes().add(theFaPackage.getComponentExchangeAllocator());
    physicalLinkEClass.getESuperTypes().add(this.getAbstractPhysicalPathLink());
    physicalLinkEClass.getESuperTypes().add(this.getAbstractPhysicalArtifact());
    physicalLinkEClass.getESuperTypes().add(this.getAbstractPathInvolvedElement());
    physicalLinkCategoryEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    physicalLinkEndEClass.getESuperTypes().add(this.getAbstractPhysicalLinkEnd());
    physicalLinkRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    physicalPathEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    physicalPathEClass.getESuperTypes().add(theFaPackage.getComponentExchangeAllocator());
    physicalPathEClass.getESuperTypes().add(this.getAbstractPathInvolvedElement());
    physicalPathEClass.getESuperTypes().add(theCapellacorePackage.getInvolverElement());
    physicalPathInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
    physicalPathReferenceEClass.getESuperTypes().add(this.getPhysicalPathInvolvement());
    physicalPathRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    physicalPortEClass.getESuperTypes().add(theInformationPackage.getPort());
    physicalPortEClass.getESuperTypes().add(this.getAbstractPhysicalArtifact());
    physicalPortEClass.getESuperTypes().add(theModellingcorePackage.getInformationsExchanger());
    physicalPortEClass.getESuperTypes().add(this.getAbstractPhysicalLinkEnd());
    physicalPortEClass.getESuperTypes().add(theInformationPackage.getProperty());
    physicalPortRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    componentPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());

    // Initialize classes and features; add operations and parameters
    initEClass(blockArchitecturePkgEClass, BlockArchitecturePkg.class, "BlockArchitecturePkg", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(blockArchitectureEClass, BlockArchitecture.class, "BlockArchitecture", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getBlockArchitecture_OwnedAbstractCapabilityPkg(), theCapellacommonPackage.getAbstractCapabilityPkg(), null, "ownedAbstractCapabilityPkg", null, 0, 1, BlockArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_OwnedInterfacePkg(), this.getInterfacePkg(), null, "ownedInterfacePkg", null, 0, 1, BlockArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_OwnedDataPkg(), theInformationPackage.getDataPkg(), null, "ownedDataPkg", null, 0, 1, BlockArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_ProvisionedArchitectureAllocations(), this.getArchitectureAllocation(), this.getArchitectureAllocation_AllocatingArchitecture(), "provisionedArchitectureAllocations", null, 0, -1, BlockArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_ProvisioningArchitectureAllocations(), this.getArchitectureAllocation(), this.getArchitectureAllocation_AllocatedArchitecture(), "provisioningArchitectureAllocations", null, 0, -1, BlockArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_AllocatedArchitectures(), this.getBlockArchitecture(), null, "allocatedArchitectures", null, 0, -1, BlockArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_AllocatingArchitectures(), this.getBlockArchitecture(), null, "allocatingArchitectures", null, 0, -1, BlockArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlockArchitecture_System(), this.getComponent(), null, "system", null, 0, 1, BlockArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(blockEClass, Block.class, "Block", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getBlock_OwnedAbstractCapabilityPkg(), theCapellacommonPackage.getAbstractCapabilityPkg(), null, "ownedAbstractCapabilityPkg", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlock_OwnedInterfacePkg(), this.getInterfacePkg(), null, "ownedInterfacePkg", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlock_OwnedDataPkg(), theInformationPackage.getDataPkg(), null, "ownedDataPkg", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBlock_OwnedStateMachines(), theCapellacommonPackage.getStateMachine(), null, "ownedStateMachines", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentArchitectureEClass, ComponentArchitecture.class, "ComponentArchitecture", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(componentEClass, Component.class, "Component", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getComponent_Actor(), ecorePackage.getEBoolean(), "actor", "false", 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getComponent_Human(), ecorePackage.getEBoolean(), "human", "false", 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getComponent_OwnedInterfaceUses(), this.getInterfaceUse(), null, "ownedInterfaceUses", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_UsedInterfaceLinks(), this.getInterfaceUse(), this.getInterfaceUse_InterfaceUser(), "usedInterfaceLinks", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_UsedInterfaces(), this.getInterface(), this.getInterface_UserComponents(), "usedInterfaces", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_OwnedInterfaceImplementations(), this.getInterfaceImplementation(), null, "ownedInterfaceImplementations", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_ImplementedInterfaceLinks(), this.getInterfaceImplementation(), this.getInterfaceImplementation_InterfaceImplementor(), "implementedInterfaceLinks", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_ImplementedInterfaces(), this.getInterface(), this.getInterface_ImplementorComponents(), "implementedInterfaces", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_OwnedComponentRealizations(), this.getComponentRealization(), null, "ownedComponentRealizations", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_RealizedComponents(), this.getComponent(), null, "realizedComponents", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_RealizingComponents(), this.getComponent(), null, "realizingComponents", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_ProvidedInterfaces(), this.getInterface(), null, "providedInterfaces", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_RequiredInterfaces(), this.getInterface(), null, "requiredInterfaces", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_ContainedComponentPorts(), theFaPackage.getComponentPort(), null, "containedComponentPorts", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_ContainedParts(), this.getPart(), null, "containedParts", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_ContainedPhysicalPorts(), this.getPhysicalPort(), null, "containedPhysicalPorts", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_OwnedPhysicalPath(), this.getPhysicalPath(), null, "ownedPhysicalPath", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_OwnedPhysicalLinks(), this.getPhysicalLink(), null, "ownedPhysicalLinks", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_OwnedPhysicalLinkCategories(), this.getPhysicalLinkCategory(), null, "ownedPhysicalLinkCategories", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponent_RepresentingParts(), this.getPart(), null, "representingParts", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(partEClass, Part.class, "Part", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPart_ProvidedInterfaces(), this.getInterface(), null, "providedInterfaces", null, 0, -1, Part.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPart_RequiredInterfaces(), this.getInterface(), null, "requiredInterfaces", null, 0, -1, Part.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPart_OwnedDeploymentLinks(), this.getAbstractDeploymentLink(), null, "ownedDeploymentLinks", null, 0, -1, Part.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPart_DeployedParts(), this.getPart(), null, "deployedParts", null, 0, -1, Part.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPart_DeployingParts(), this.getPart(), null, "deployingParts", null, 0, -1, Part.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPart_OwnedAbstractType(), theModellingcorePackage.getAbstractType(), null, "ownedAbstractType", null, 0, 1, Part.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(architectureAllocationEClass, ArchitectureAllocation.class, "ArchitectureAllocation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getArchitectureAllocation_AllocatedArchitecture(), this.getBlockArchitecture(), this.getBlockArchitecture_ProvisioningArchitectureAllocations(), "allocatedArchitecture", null, 1, 1, ArchitectureAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getArchitectureAllocation_AllocatingArchitecture(), this.getBlockArchitecture(), this.getBlockArchitecture_ProvisionedArchitectureAllocations(), "allocatingArchitecture", null, 1, 1, ArchitectureAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentRealizationEClass, ComponentRealization.class, "ComponentRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentRealization_RealizedComponent(), this.getComponent(), null, "realizedComponent", null, 0, 1, ComponentRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentRealization_RealizingComponent(), this.getComponent(), null, "realizingComponent", null, 0, 1, ComponentRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interfacePkgEClass, InterfacePkg.class, "InterfacePkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInterfacePkg_OwnedInterfaces(), this.getInterface(), null, "ownedInterfaces", null, 0, -1, InterfacePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterfacePkg_OwnedInterfacePkgs(), this.getInterfacePkg(), null, "ownedInterfacePkgs", null, 0, -1, InterfacePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interfaceEClass, Interface.class, "Interface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getInterface_Mechanism(), ecorePackage.getEString(), "mechanism", null, 0, 1, Interface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getInterface_Structural(), ecorePackage.getEBoolean(), "structural", "true", 0, 1, Interface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getInterface_ImplementorComponents(), this.getComponent(), this.getComponent_ImplementedInterfaces(), "implementorComponents", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_UserComponents(), this.getComponent(), this.getComponent_UsedInterfaces(), "userComponents", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_InterfaceImplementations(), this.getInterfaceImplementation(), null, "interfaceImplementations", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_InterfaceUses(), this.getInterfaceUse(), null, "interfaceUses", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_ProvisioningInterfaceAllocations(), this.getInterfaceAllocation(), this.getInterfaceAllocation_AllocatedInterface(), "provisioningInterfaceAllocations", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_AllocatingInterfaces(), this.getInterface(), null, "allocatingInterfaces", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_AllocatingComponents(), this.getComponent(), null, "allocatingComponents", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_ExchangeItems(), theInformationPackage.getExchangeItem(), null, "exchangeItems", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_OwnedExchangeItemAllocations(), this.getExchangeItemAllocation(), null, "ownedExchangeItemAllocations", null, 0, -1, Interface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_RequiringComponents(), this.getComponent(), null, "requiringComponents", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_RequiringComponentPorts(), theFaPackage.getComponentPort(), null, "requiringComponentPorts", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_ProvidingComponents(), this.getComponent(), null, "providingComponents", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_ProvidingComponentPorts(), theFaPackage.getComponentPort(), null, "providingComponentPorts", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_RealizingLogicalInterfaces(), this.getInterface(), null, "realizingLogicalInterfaces", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_RealizedContextInterfaces(), this.getInterface(), null, "realizedContextInterfaces", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_RealizingPhysicalInterfaces(), this.getInterface(), null, "realizingPhysicalInterfaces", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterface_RealizedLogicalInterfaces(), this.getInterface(), null, "realizedLogicalInterfaces", null, 0, -1, Interface.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interfaceImplementationEClass, InterfaceImplementation.class, "InterfaceImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInterfaceImplementation_InterfaceImplementor(), this.getComponent(), this.getComponent_ImplementedInterfaceLinks(), "interfaceImplementor", null, 1, 1, InterfaceImplementation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterfaceImplementation_ImplementedInterface(), this.getInterface(), null, "implementedInterface", null, 1, 1, InterfaceImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interfaceUseEClass, InterfaceUse.class, "InterfaceUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInterfaceUse_InterfaceUser(), this.getComponent(), this.getComponent_UsedInterfaceLinks(), "interfaceUser", null, 1, 1, InterfaceUse.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterfaceUse_UsedInterface(), this.getInterface(), null, "usedInterface", null, 1, 1, InterfaceUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(providedInterfaceLinkEClass, ProvidedInterfaceLink.class, "ProvidedInterfaceLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getProvidedInterfaceLink_Interface(), this.getInterface(), null, "interface", null, 1, 1, ProvidedInterfaceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(requiredInterfaceLinkEClass, RequiredInterfaceLink.class, "RequiredInterfaceLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getRequiredInterfaceLink_Interface(), this.getInterface(), null, "interface", null, 1, 1, RequiredInterfaceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interfaceAllocationEClass, InterfaceAllocation.class, "InterfaceAllocation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInterfaceAllocation_AllocatedInterface(), this.getInterface(), this.getInterface_ProvisioningInterfaceAllocations(), "allocatedInterface", null, 1, 1, InterfaceAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterfaceAllocation_AllocatingInterfaceAllocator(), this.getInterfaceAllocator(), this.getInterfaceAllocator_ProvisionedInterfaceAllocations(), "allocatingInterfaceAllocator", null, 1, 1, InterfaceAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

    initEClass(interfaceAllocatorEClass, InterfaceAllocator.class, "InterfaceAllocator", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInterfaceAllocator_OwnedInterfaceAllocations(), this.getInterfaceAllocation(), null, "ownedInterfaceAllocations", null, 0, -1, InterfaceAllocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterfaceAllocator_ProvisionedInterfaceAllocations(), this.getInterfaceAllocation(), this.getInterfaceAllocation_AllocatingInterfaceAllocator(), "provisionedInterfaceAllocations", null, 0, -1, InterfaceAllocator.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInterfaceAllocator_AllocatedInterfaces(), this.getInterface(), null, "allocatedInterfaces", null, 0, -1, InterfaceAllocator.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeItemAllocationEClass, ExchangeItemAllocation.class, "ExchangeItemAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getExchangeItemAllocation_SendProtocol(), theCommunicationPackage.getCommunicationLinkProtocol(), "sendProtocol", null, 0, 1, ExchangeItemAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getExchangeItemAllocation_ReceiveProtocol(), theCommunicationPackage.getCommunicationLinkProtocol(), "receiveProtocol", null, 0, 1, ExchangeItemAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItemAllocation_AllocatedItem(), theInformationPackage.getExchangeItem(), null, "allocatedItem", null, 0, 1, ExchangeItemAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItemAllocation_AllocatingInterface(), this.getInterface(), null, "allocatingInterface", null, 0, 1, ExchangeItemAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(deployableElementEClass, DeployableElement.class, "DeployableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getDeployableElement_DeployingLinks(), this.getAbstractDeploymentLink(), null, "deployingLinks", null, 0, -1, DeployableElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(deploymentTargetEClass, DeploymentTarget.class, "DeploymentTarget", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getDeploymentTarget_DeploymentLinks(), this.getAbstractDeploymentLink(), null, "deploymentLinks", null, 0, -1, DeploymentTarget.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractDeploymentLinkEClass, AbstractDeploymentLink.class, "AbstractDeploymentLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractDeploymentLink_DeployedElement(), this.getDeployableElement(), null, "deployedElement", null, 1, 1, AbstractDeploymentLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractDeploymentLink_Location(), this.getDeploymentTarget(), null, "location", null, 1, 1, AbstractDeploymentLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractPathInvolvedElementEClass, AbstractPathInvolvedElement.class, "AbstractPathInvolvedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(abstractPhysicalArtifactEClass, AbstractPhysicalArtifact.class, "AbstractPhysicalArtifact", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractPhysicalArtifact_AllocatorConfigurationItems(), theEpbsPackage.getConfigurationItem(), theEpbsPackage.getConfigurationItem_AllocatedPhysicalArtifacts(), "allocatorConfigurationItems", null, 0, -1, AbstractPhysicalArtifact.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractPhysicalLinkEndEClass, AbstractPhysicalLinkEnd.class, "AbstractPhysicalLinkEnd", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractPhysicalLinkEnd_InvolvedLinks(), this.getPhysicalLink(), null, "involvedLinks", null, 0, -1, AbstractPhysicalLinkEnd.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractPhysicalPathLinkEClass, AbstractPhysicalPathLink.class, "AbstractPhysicalPathLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(physicalLinkEClass, PhysicalLink.class, "PhysicalLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalLink_LinkEnds(), this.getAbstractPhysicalLinkEnd(), null, "linkEnds", null, 2, 2, PhysicalLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_OwnedComponentExchangeFunctionalExchangeAllocations(), theFaPackage.getComponentExchangeFunctionalExchangeAllocation(), null, "ownedComponentExchangeFunctionalExchangeAllocations", null, 0, -1, PhysicalLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_OwnedPhysicalLinkEnds(), this.getPhysicalLinkEnd(), null, "ownedPhysicalLinkEnds", null, 0, -1, PhysicalLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_OwnedPhysicalLinkRealizations(), this.getPhysicalLinkRealization(), null, "ownedPhysicalLinkRealizations", null, 0, -1, PhysicalLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_Categories(), this.getPhysicalLinkCategory(), null, "categories", null, 0, -1, PhysicalLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_SourcePhysicalPort(), this.getPhysicalPort(), null, "sourcePhysicalPort", null, 0, 1, PhysicalLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_TargetPhysicalPort(), this.getPhysicalPort(), null, "targetPhysicalPort", null, 0, 1, PhysicalLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_RealizedPhysicalLinks(), this.getPhysicalLink(), null, "realizedPhysicalLinks", null, 0, -1, PhysicalLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLink_RealizingPhysicalLinks(), this.getPhysicalLink(), null, "realizingPhysicalLinks", null, 0, -1, PhysicalLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalLinkCategoryEClass, PhysicalLinkCategory.class, "PhysicalLinkCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalLinkCategory_Links(), this.getPhysicalLink(), null, "links", null, 0, -1, PhysicalLinkCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalLinkEndEClass, PhysicalLinkEnd.class, "PhysicalLinkEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalLinkEnd_Port(), this.getPhysicalPort(), null, "port", null, 0, 1, PhysicalLinkEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalLinkEnd_Part(), this.getPart(), null, "part", null, 0, 1, PhysicalLinkEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalLinkRealizationEClass, PhysicalLinkRealization.class, "PhysicalLinkRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(physicalPathEClass, PhysicalPath.class, "PhysicalPath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalPath_InvolvedLinks(), this.getAbstractPhysicalPathLink(), null, "involvedLinks", null, 0, -1, PhysicalPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPath_OwnedPhysicalPathInvolvements(), this.getPhysicalPathInvolvement(), null, "ownedPhysicalPathInvolvements", null, 0, -1, PhysicalPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPath_FirstPhysicalPathInvolvements(), this.getPhysicalPathInvolvement(), null, "firstPhysicalPathInvolvements", null, 0, -1, PhysicalPath.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPath_OwnedPhysicalPathRealizations(), this.getPhysicalPathRealization(), null, "ownedPhysicalPathRealizations", null, 0, -1, PhysicalPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPath_RealizedPhysicalPaths(), this.getPhysicalPath(), null, "realizedPhysicalPaths", null, 0, -1, PhysicalPath.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPath_RealizingPhysicalPaths(), this.getPhysicalPath(), null, "realizingPhysicalPaths", null, 0, -1, PhysicalPath.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalPathInvolvementEClass, PhysicalPathInvolvement.class, "PhysicalPathInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalPathInvolvement_NextInvolvements(), this.getPhysicalPathInvolvement(), null, "nextInvolvements", null, 0, -1, PhysicalPathInvolvement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPathInvolvement_PreviousInvolvements(), this.getPhysicalPathInvolvement(), null, "previousInvolvements", null, 0, -1, PhysicalPathInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPathInvolvement_InvolvedElement(), this.getAbstractPathInvolvedElement(), null, "involvedElement", null, 0, 1, PhysicalPathInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPathInvolvement_InvolvedComponent(), this.getComponent(), null, "involvedComponent", null, 0, 1, PhysicalPathInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalPathReferenceEClass, PhysicalPathReference.class, "PhysicalPathReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalPathReference_ReferencedPhysicalPath(), this.getPhysicalPath(), null, "referencedPhysicalPath", null, 0, 1, PhysicalPathReference.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalPathRealizationEClass, PhysicalPathRealization.class, "PhysicalPathRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(physicalPortEClass, PhysicalPort.class, "PhysicalPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalPort_OwnedComponentPortAllocations(), theFaPackage.getComponentPortAllocation(), null, "ownedComponentPortAllocations", null, 0, -1, PhysicalPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPort_OwnedPhysicalPortRealizations(), this.getPhysicalPortRealization(), null, "ownedPhysicalPortRealizations", null, 0, -1, PhysicalPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPort_AllocatedComponentPorts(), theFaPackage.getComponentPort(), theFaPackage.getComponentPort_AllocatingPhysicalPorts(), "allocatedComponentPorts", null, 0, -1, PhysicalPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPort_RealizedPhysicalPorts(), this.getPhysicalPort(), null, "realizedPhysicalPorts", null, 0, -1, PhysicalPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalPort_RealizingPhysicalPorts(), this.getPhysicalPort(), null, "realizingPhysicalPorts", null, 0, -1, PhysicalPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalPortRealizationEClass, PhysicalPortRealization.class, "PhysicalPortRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(componentPkgEClass, ComponentPkg.class, "ComponentPkg", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedParts(), this.getPart(), null, "ownedParts", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedComponentExchanges(), theFaPackage.getComponentExchange(), null, "ownedComponentExchanges", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedComponentExchangeCategories(), theFaPackage.getComponentExchangeCategory(), null, "ownedComponentExchangeCategories", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedFunctionalLinks(), theFaPackage.getExchangeLink(), null, "ownedFunctionalLinks", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedFunctionalAllocations(), theFaPackage.getComponentFunctionalAllocation(), null, "ownedFunctionalAllocations", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedComponentExchangeRealizations(), theFaPackage.getComponentExchangeRealization(), null, "ownedComponentExchangeRealizations", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedPhysicalLinks(), this.getPhysicalLink(), null, "ownedPhysicalLinks", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedPhysicalLinkCategories(), this.getPhysicalLinkCategory(), null, "ownedPhysicalLinkCategories", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPkg_OwnedStateMachines(), theCapellacommonPackage.getStateMachine(), null, "ownedStateMachines", null, 0, -1, ComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Create resource
    createResource(eNS_URI);

    // Create annotations
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
    // http://www.polarsys.org/capella/2007/UML2Mapping
    createUML2MappingAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
    createSegmentAnnotations();
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore
    createIgnoreAnnotations();
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
         "description", "CompositeStructure aims at defining the common component approach composite structure pattern language (close to the UML Composite structure).\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model FunctionalAnalysis.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockArchitecturePkgEClass,
       source,
       new String[] {
         "description", "Container package for BlockArchitecture elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockArchitectureEClass,
       source,
       new String[] {
         "description", "Parent class for deriving specific architectures for each design phase\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "description", "Link to packages that contain capabilities\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedInterfacePkg(),
       source,
       new String[] {
         "description", "Link to packages that contain interfaces\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedDataPkg(),
       source,
       new String[] {
         "description", "Link to packages that contain data\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_ProvisionedArchitectureAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) list of allocation links to other architectures\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_ProvisioningArchitectureAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) list of allocation links from other architectures to this one\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_AllocatedArchitectures(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the BlockArchitectures that are allocated from this one\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_AllocatingArchitectures(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to BlockArchitectures that allocate to this architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_System(),
       source,
       new String[] {
         "description", "The system component of the architecture block.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockEClass,
       source,
       new String[] {
         "description", "A modular unit that describes the structure of a system or element.\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a (abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "description", "Link to packages that contain capabilities\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedInterfacePkg(),
       source,
       new String[] {
         "description", "Link to packages that contain interfaces\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedDataPkg(),
       source,
       new String[] {
         "description", "Link to packages that contain data\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedStateMachines(),
       source,
       new String[] {
         "description", "Link to related state machines\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentArchitectureEClass,
       source,
       new String[] {
         "description", "A specialized kind of BlockArchitecture, serving as a parent class for the various architecture levels, from System analysis down to EPBS architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "N/A (abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentEClass,
       source,
       new String[] {
         "description", "An entity, with discrete structure within the system, that interacts with other Components of the system, thereby contributing at its lowest level to the system properties and characteristics.\r\n[source: Sys EM , ISO/IEC CD 15288]", //$NON-NLS-1$ //$NON-NLS-2$
         "arcadia_description", "A component is a constituent part of the system, contributing to its behaviour, by interacting with other components and external actors, thereby contributing at its lowest level to the system properties and characteristics. Example: radio receiver, graphical user interface...\r\nDifferent kinds of components exist: see below (logical component, physical component...).", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a (abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_Actor(),
       source,
       new String[] {
         "description", "Indicates if the Component is an Actor", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_Human(),
       source,
       new String[] {
         "description", "Indicates whether the Component is a Human", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedInterfaceUses(),
       source,
       new String[] {
         "description", "InterfaceUse relationships that are stored/owned under this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaceLinks(),
       source,
       new String[] {
         "description", "(automatically computed) interfaceUse relationships that involve this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaces(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the Interfaces being used by this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedInterfaceImplementations(),
       source,
       new String[] {
         "description", "Interface implementation relationships that are stored/owned under this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaceLinks(),
       source,
       new String[] {
         "description", "(automatically computed) list of InterfaceImplementation links that involve this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaces(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the Interfaces being implemented by this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedComponentRealizations(),
       source,
       new String[] {
         "description", "Component Realization links owned by this Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RealizedComponents(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the components being allocated from this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RealizingComponents(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the components allocating this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ProvidedInterfaces(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the Interfaces being provided by this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RequiredInterfaces(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the Interfaces being required by this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedPhysicalPath(),
       source,
       new String[] {
         "description", "the PhysicalPaths that are stored/owned by this physical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedPhysicalLinks(),
       source,
       new String[] {
         "description", "Physical links contained in / owned by this Physical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedPhysicalLinkCategories(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RepresentingParts(),
       source,
       new String[] {
         "description", "Parts that represent this Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (partEClass,
       source,
       new String[] {
         "description", "In SysML, a Part is an owned property of a Block\r\n[source: SysML glossary for SysML v1.0]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_ProvidedInterfaces(),
       source,
       new String[] {
         "description", "(computed) the provided interfaces associated to the classifier that this part represents\r\n[source: Capella study]\r\n\r\nThe interfaces that the component exposes to its environment.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_RequiredInterfaces(),
       source,
       new String[] {
         "description", "(computed) the required interfaces associated to the classifier that this part represents\r\n[source: Capella study]\r\n\r\nThe interfaces that the component requires from other components in its environment in order to be able to offer\r\nits full set of provided functionality\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_OwnedDeploymentLinks(),
       source,
       new String[] {
         "description", "Deployment relationships that are stored/owned under this part", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (architectureAllocationEClass,
       source,
       new String[] {
         "description", "Mediator class between BlockArchitecture elements, to represent an allocation link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getArchitectureAllocation_AllocatedArchitecture(),
       source,
       new String[] {
         "description", "Specifies the allocated architecture\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getArchitectureAllocation_AllocatingArchitecture(),
       source,
       new String[] {
         "description", "Specifies the allocating architecture\r\n[source: Capella study]\r\n\r\nSpecifies the sources of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentRealizationEClass,
       source,
       new String[] {
         "description", "Mediator class between Component elements, representing the realization link between these elements\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentRealization_RealizedComponent(),
       source,
       new String[] {
         "description", "Specifies the allocated component\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentRealization_RealizingComponent(),
       source,
       new String[] {
         "description", "Specifies the allocating component\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfacePkgEClass,
       source,
       new String[] {
         "description", "A container for Interface elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfaces(),
       source,
       new String[] {
         "description", "Specifies the interfaces that are owned by this Package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfacePkgs(),
       source,
       new String[] {
         "description", "Specifies the packages of interfaces that are owned by this Package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceEClass,
       source,
       new String[] {
         "description", "An interface is a kind of classifier that represents a declaration of a set of coherent public features and obligations. An\r\ninterface specifies a contract; any instance of a classifier that realizes the interface must fulfill that contract.\r\n[source: UML superstructure v2.2]\r\n\r\nInterfaces are defined by functional and physical characteristics that exist at a common boundary with co-functioning items and allow systems, equipment, software, and system data to be compatible.\r\n[source: not precised]\r\n\r\nThat design feature of one piece of equipment that affects a design feature of another piece of equipment. \r\nAn interface can extend beyond the physical boundary between two items. (For example, the weight and center of gravity of one item can affect the interfacing item; however, the center of gravity is rarely located at the physical boundary.\r\nAn electrical interface generally extends to the first isolating element rather than terminating at a series of connector pins.)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "In Capella, Interfaces are created to declare the nature of interactions between the System and external actors.", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system/logical/physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/external_interface_example.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_Mechanism(),
       source,
       new String[] {
         "description", "_todo_reviewed : cannot find the meaning of this attribute ? How to fill it ?", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "_todo_reviewed : to be precised", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_Structural(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ImplementorComponents(),
       source,
       new String[] {
         "description", "references to the components that implement this interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_UserComponents(),
       source,
       new String[] {
         "description", "references to the components that use this interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceImplementations(),
       source,
       new String[] {
         "description", "references to the InterfaceImplementation elements, that act as mediators between this interface and its implementers\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceUses(),
       source,
       new String[] {
         "description", "references to the InterfaceUse elements, that act as mediator classes between this interface and its users\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvisioningInterfaceAllocations(),
       source,
       new String[] {
         "description", "References to the InterfaceAllocation elements, acting as mediator classes between the interface and the elements to which/from which it is allocated\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_AllocatingInterfaces(),
       source,
       new String[] {
         "description", "References to the Interfaces that declare an allocation link to this Interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_AllocatingComponents(),
       source,
       new String[] {
         "description", "References to the components that declare an allocation link to this Interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ExchangeItems(),
       source,
       new String[] {
         "description", "References to all exchange items allocated by the interface", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_OwnedExchangeItemAllocations(),
       source,
       new String[] {
         "description", "References to allocations of exchange items", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceImplementationEClass,
       source,
       new String[] {
         "description", "Mediator class between an Interface and its implementor (typically a Component)\r\n[source: Capella study]\r\n\r\nAn InterfaceRealization is a specialized Realization relationship between a Classifier and an Interface. This relationship\r\nsignifies that the realizing classifier conforms to the contract specified by the Interface.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_InterfaceImplementor(),
       source,
       new String[] {
         "description", "References the Component that owns this Interface implementation.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_ImplementedInterface(),
       source,
       new String[] {
         "description", "References the Interface specifying the conformance contract\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceUseEClass,
       source,
       new String[] {
         "description", "Mediator class between an interface and its user (typically a Component)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_InterfaceUser(),
       source,
       new String[] {
         "description", "Component that uses the interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_UsedInterface(),
       source,
       new String[] {
         "description", "Supplied interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (providedInterfaceLinkEClass,
       source,
       new String[] {
         "description", "(not used)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "not used/implemented as of Capella 1.0.3", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProvidedInterfaceLink_Interface(),
       source,
       new String[] {
         "description", "References the Interface specifying the conformance contract\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (requiredInterfaceLinkEClass,
       source,
       new String[] {
         "description", "(not used)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "not used/implemented as of Capella", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getRequiredInterfaceLink_Interface(),
       source,
       new String[] {
         "description", "The element(s) independent of the client element(s), in the same respect and the same dependency relationship\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceAllocationEClass,
       source,
       new String[] {
         "description", "Mediator class between an Interface and an element that allocates to/from it.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocation_AllocatedInterface(),
       source,
       new String[] {
         "description", "Specifies the allocated interface\r\n[source: Capella study]\r\n\r\nSpecifies the targets of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocation_AllocatingInterfaceAllocator(),
       source,
       new String[] {
         "description", "Specifies the allocating interface\r\n[source: Capella study]\r\n\r\nSpecifies the sources of the DirectedRelationship.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceAllocatorEClass,
       source,
       new String[] {
         "description", "Base class for elements that need to be involved in an allocation link to/from an Interface\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_OwnedInterfaceAllocations(),
       source,
       new String[] {
         "description", "the interface allocation links that are stored/owned under this interface allocator\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_ProvisionedInterfaceAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) the interface allocation links involving this interface allocator\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_AllocatedInterfaces(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the Interfaces being allocated by this interface allocator\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemAllocationEClass,
       source,
       new String[] {
         "description", "Allocation link between exchange items and interface that support them", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_SendProtocol(),
       source,
       new String[] {
         "description", "describes the default protocol used by the sender of the exchange item. It could be overrided by the protocol used by the given communication exchanger", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to CommunicationLinkProtocol definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_ReceiveProtocol(),
       source,
       new String[] {
         "description", "describes the default protocol used by the receiver of the exchange item. It could be overrided by the protocol used by the given communication exchanger", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to CommunicationLinkProtocol definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_AllocatedItem(),
       source,
       new String[] {
         "description", "the exchange item that is being allocated by the interface", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_AllocatingInterface(),
       source,
       new String[] {
         "description", "the interface that allocated the given exchange item", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deployableElementEClass,
       source,
       new String[] {
         "description", "characterizes a physical model element that is intended to be deployed on a given (physical) target\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeployableElement_DeployingLinks(),
       source,
       new String[] {
         "description", "the list of deployment specifications associated to this element, e.g. associations between this element and a physical location to which it is to be deployed\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deploymentTargetEClass,
       source,
       new String[] {
         "description", "the physical target that will host a deployable element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeploymentTarget_DeploymentLinks(),
       source,
       new String[] {
         "description", "the list of deployment specifications involving this physical target as the destination of the deployment\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractDeploymentLinkEClass,
       source,
       new String[] {
         "description", "the link between a physical element, and the physical target onto which it is deployed\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_DeployedElement(),
       source,
       new String[] {
         "description", "the physical element involved in this relationship, that is to be deployed on the target\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_Location(),
       source,
       new String[] {
         "description", "the host where the source element involved in this relationship will be deployed\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPathInvolvedElementEClass,
       source,
       new String[] {
         "description", "An involved element is a capella element that is, at least, involved in an involvement relationship with the role of the element that is involved\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPhysicalArtifactEClass,
       source,
       new String[] {
         "description", "A physical artifact is any physical element in the physical architecture (component, port, link,...).\r\nThese artifacts will be part allocated to configuration items in the EPBS.", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPhysicalLinkEndEClass,
       source,
       new String[] {
         "description", "End of a physical link", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractPhysicalLinkEnd_InvolvedLinks(),
       source,
       new String[] {
         "description", "Physical links that come in or out of this physical port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPhysicalPathLinkEClass,
       source,
       new String[] {
         "description", "the base element for building a physical path : a link between two physical interfaces\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkEClass,
       source,
       new String[] {
         "description", "the representation of the physical medium connecting two physical interfaces\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_LinkEnds(),
       source,
       new String[] {
         "description", "the source(s) and destination(s) of this physical link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_OwnedComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "description", "the allocations between component exchanges and functional exchanges, that are owned by this physical link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_OwnedPhysicalLinkEnds(),
       source,
       new String[] {
         "description", "the physical link endpoints involved in this link\r\n\r\nA connector consists of at least two connector ends, each representing the participation of instances of the classifiers\r\ntyping the connectable elements attached to this end. The set of connector ends is ordered.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_OwnedPhysicalLinkRealizations(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_RealizedPhysicalLinks(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_RealizingPhysicalLinks(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkEndEClass,
       source,
       new String[] {
         "description", "an endpoint of a physical link\r\n\r\nA connector end is an endpoint of a connector, which attaches the connector to a connectable element. Each connector\r\nend is part of one connector.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Port(),
       source,
       new String[] {
         "description", "the port to which this communication endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Part(),
       source,
       new String[] {
         "description", "the part to which this connect endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkRealizationEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathEClass,
       source,
       new String[] {
         "description", "the specification of a given path of informations flowing across physical links and interfaces.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this is the equivalent for the physical architecture, of a functional chain defined at system level", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_InvolvedLinks(),
       source,
       new String[] {
         "description", "the list of steps of this physical path\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_OwnedPhysicalPathRealizations(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_RealizedPhysicalPaths(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_RealizingPhysicalPaths(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathRealizationEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPortEClass,
       source,
       new String[] {
         "description", "A port on a physical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_OwnedPhysicalPortRealizations(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_RealizedPhysicalPorts(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_RealizingPhysicalPorts(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPortRealizationEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPkgEClass,
       source,
       new String[] {
         "description", "a package containing parts", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedParts(),
       source,
       new String[] {
         "description", "Parts stored in this Component Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchanges(),
       source,
       new String[] {
         "description", "the connections between components, contained in this structure\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchangeCategories(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "description", "the (functional) exchange links defined in the context of this structure\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalAllocations(),
       source,
       new String[] {
         "description", "the list of component <=> function allocation links defined in this structure\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "description", "the list of realizations links between component exchanges, defined in this structure\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedPhysicalLinks(),
       source,
       new String[] {
         "description", "Physical Links contained in this Component Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedPhysicalLinkCategories(),
       source,
       new String[] {
         "description", "Physical Links contained in this Component Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedStateMachines(),
       source,
       new String[] {
         "description", "Physical Links contained in this Component Package", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getBlockArchitecture_OwnedInterfacePkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlockArchitecture_OwnedDataPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedInterfacePkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedDataPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedStateMachines(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_Actor(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_Human(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_UsedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_ImplementedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_RealizedComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_RealizingComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ProvidedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_RequiredInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_ContainedComponentPorts(),
       source,
       new String[] {
         "feature", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedParts(),
       source,
       new String[] {
         "feature", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedPhysicalPorts(),
       source,
       new String[] {
         "feature", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedPhysicalPath(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_OwnedPhysicalLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_OwnedPhysicalLinkCategories(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_RepresentingParts(),
       source,
       new String[] {
       });
    addAnnotation
      (partEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPart_DeployedParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getPart_DeployingParts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_OwnedAbstractType(),
       source,
       new String[] {
       });
    addAnnotation
      (interfacePkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfacePkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (interfaceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_Mechanism(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_ImplementorComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_UserComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ExchangeItems(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_OwnedExchangeItemAllocations(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_RequiringComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RequiringComponentPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvidingComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvidingComponentPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizingLogicalInterfaces(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizedContextInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_RealizingPhysicalInterfaces(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizedLogicalInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (exchangeItemAllocationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemAllocation_SendProtocol(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemAllocation_ReceiveProtocol(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemAllocation_AllocatedItem(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractDeploymentLink_DeployedElement(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractDeploymentLink_Location(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractPhysicalArtifact_AllocatorConfigurationItems(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractPhysicalLinkEnd_InvolvedLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_LinkEnds(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_OwnedPhysicalLinkEnds(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_Categories(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_SourcePhysicalPort(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_TargetPhysicalPort(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_RealizedPhysicalLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLink_RealizingPhysicalLinks(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkCategoryEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLinkCategory_Links(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkEndEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLinkEnd_Port(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLinkEnd_Part(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalPathEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPath_OwnedPhysicalPathInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPath_FirstPhysicalPathInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPath_RealizedPhysicalPaths(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPath_RealizingPhysicalPaths(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathInvolvementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPathInvolvement_NextInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPathInvolvement_PreviousInvolvements(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_InvolvedElement(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalPathReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPathReference_ReferencedPhysicalPath(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalPortEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPort_AllocatedComponentPorts(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPort_RealizedPhysicalPorts(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalPort_RealizingPhysicalPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchangeCategories(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedPhysicalLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedPhysicalLinkCategories(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedStateMachines(),
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
   * Initializes the annotations for <b>http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createMappingAnnotations() {
    String source = "http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping"; //$NON-NLS-1$
    addAnnotation
      (blockArchitecturePkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockArchitectureEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which AbstractCapabilityPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedInterfacePkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which InterfacePkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedDataPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_ProvisionedArchitectureAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_ProvisioningArchitectureAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_AllocatedArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_AllocatingArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::BehavioredClassifier", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Descendants are mapped to SysML::Blocks::Block, which cannot contain a Package.\r\nTherefore, store these AbstractCapabilityPackages in the nearest available package.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedInterfacePkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which InterfacePkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedDataPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedStateMachines(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::BehavioredClassifier::ownedBehavior", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::BehavioredClassifier::ownedBehavior elements on which StateMachine stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentArchitectureEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedInterfaceUses(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which InterfaceUse stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaceLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedInterfaceImplementations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::BehavioredClassifier::interfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaceLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RealizedComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RealizingComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ProvidedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RequiredInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedPhysicalPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedPhysicalPath(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Blocks::Block cannot contain PhysicalPath\'s equivalent, hence we find the nearest available package to store them.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedPhysicalLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::StructuredClassifier::ownedConnector", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "since PhysicalLink is mapped to uml::Connector", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (partEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "should be mapped to uml::Property, but one of its concrete ancestors already is (Property), so avoid redefining it\r\nat this level to avoid profile generation issue", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "information::Property must have as base metaclass uml::Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_ProvidedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_RequiredInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_OwnedDeploymentLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_DeployedParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_DeployingParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (architectureAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getArchitectureAllocation_AllocatedArchitecture(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getArchitectureAllocation_AllocatingArchitecture(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentRealization_RealizedComponent(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentRealization_RealizingComponent(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfacePkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which Interface stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfacePkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which InterfacePkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Interface", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_Mechanism(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ImplementorComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_UserComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceImplementations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::InterfaceRealization::contract", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Element::ownedElement elements on which InterfaceImplementation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceUses(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Element::ownedElement elements on which InterfaceUse stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvisioningInterfaceAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_AllocatingInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_AllocatingComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ExchangeItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_OwnedExchangeItemAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RequiringComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RequiringComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvidingComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvidingComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizingLogicalInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizedContextInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizingPhysicalInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizedLogicalInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceImplementationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::InterfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_InterfaceImplementor(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_ImplementedInterface(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InterfaceRealization::contract", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceUseEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Usage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_InterfaceUser(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::client", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_UsedInterface(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (providedInterfaceLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::InterfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProvidedInterfaceLink_Interface(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InterfaceRealization::contract", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (requiredInterfaceLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Usage", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getRequiredInterfaceLink_Interface(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Dependency::supplier elements on which Interface stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocation_AllocatedInterface(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocation_AllocatingInterfaceAllocator(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceAllocatorEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Classifier", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_OwnedInterfaceAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which InterfaceAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_ProvisionedInterfaceAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_AllocatedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_SendProtocol(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_ReceiveProtocol(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_AllocatedItem(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_AllocatingInterface(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deployableElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::NamedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeployableElement_DeployingLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deploymentTargetEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::DeploymentTarget", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeploymentTarget_DeploymentLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::DeploymentTarget::deployment elements on which AbstractDeployment stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractDeploymentLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Dependency,could be mapped on uml::Deployment, but dependencies diagram allows to \"deploy\" more capella element types.", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_DeployedElement(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_Location(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::client", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Dependency::client elements on which DeploymentTarget stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPathInvolvedElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPhysicalArtifactEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractPhysicalArtifact_AllocatorConfigurationItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPhysicalLinkEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractPhysicalLinkEnd_InvolvedLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractPhysicalPathLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Connector", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_LinkEnds(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "first need to create ConnectorEnds pointing to the Ports, and then reference them in uml::Connector::end", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "cardinality must be [2..2]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_OwnedComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "some elements on which ComponentFunctionalExchangeAllocation stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_OwnedPhysicalLinkEnds(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Connector::end", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_OwnedPhysicalLinkRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_SourcePhysicalPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_TargetPhysicalPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ConnectorEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Port(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorEnd::role", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::ConnectorEnd::role elements on which PhysicalPort stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Part(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorEnd::partWithPort", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_InvolvedLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_OwnedPhysicalPathInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_FirstPhysicalPathInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_OwnedPhysicalPathRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathInvolvementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_NextInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_PreviousInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_InvolvedElement(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_InvolvedComponent(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathReference_ReferencedPhysicalPath(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPathRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPortEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::PortAndFlows::FlowPort", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_OwnedPhysicalPortRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_AllocatedComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalPortRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ComponentExchange stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ExchangeLink stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ComponentFunctionalAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ComponentExchangeRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
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
      (blockArchitectureEClass,
       source,
       new String[] {
         "Label", "BlockArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "Label", "ownedAspectPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedInterfacePkg(),
       source,
       new String[] {
         "Label", "ownedInterfacePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedDataPkg(),
       source,
       new String[] {
         "Label", "ownedDataPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockEClass,
       source,
       new String[] {
         "Label", "Block" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "Label", "aspectPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedInterfacePkg(),
       source,
       new String[] {
         "Label", "ownedInterfacePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedDataPkg(),
       source,
       new String[] {
         "Label", "ownedDataPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentArchitectureEClass,
       source,
       new String[] {
         "Label", "ComponentArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentEClass,
       source,
       new String[] {
         "Label", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedInterfaceUses(),
       source,
       new String[] {
         "Label", "ownedInterfaceUses" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaceLinks(),
       source,
       new String[] {
         "Label", "usedInterfaceLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaces(),
       source,
       new String[] {
         "Label", "usedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaceLinks(),
       source,
       new String[] {
         "Label", "realizedInterfaceLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaces(),
       source,
       new String[] {
         "Label", "implementedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ProvidedInterfaces(),
       source,
       new String[] {
         "Label", "providedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RequiredInterfaces(),
       source,
       new String[] {
         "Label", "requiredInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (partEClass,
       source,
       new String[] {
         "Label", "PhysicalPart" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_ProvidedInterfaces(),
       source,
       new String[] {
         "Label", "providedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_RequiredInterfaces(),
       source,
       new String[] {
         "Label", "requiredInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (architectureAllocationEClass,
       source,
       new String[] {
         "Label", "ArchitectureAllocation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfacePkgEClass,
       source,
       new String[] {
         "Label", "InterfacePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfaces(),
       source,
       new String[] {
         "Label", "ownedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfacePkgs(),
       source,
       new String[] {
         "Label", "subInterfacePkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceEClass,
       source,
       new String[] {
         "Label", "Interface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ImplementorComponents(),
       source,
       new String[] {
         "Label", "implementorComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_UserComponents(),
       source,
       new String[] {
         "Label", "userComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceImplementations(),
       source,
       new String[] {
         "Label", "interfaceImplementations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceUses(),
       source,
       new String[] {
         "Label", "interfaceUses" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceImplementationEClass,
       source,
       new String[] {
         "Label", "InterfaceImplementation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_InterfaceImplementor(),
       source,
       new String[] {
         "Label", "Interface Implementor" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_ImplementedInterface(),
       source,
       new String[] {
         "Label", "realizedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceUseEClass,
       source,
       new String[] {
         "Label", "InterfaceUse" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_InterfaceUser(),
       source,
       new String[] {
         "Label", "interfaceUser" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_UsedInterface(),
       source,
       new String[] {
         "Label", "usedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (providedInterfaceLinkEClass,
       source,
       new String[] {
         "Label", "ProvidedInterfaceLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProvidedInterfaceLink_Interface(),
       source,
       new String[] {
         "Label", "interface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (requiredInterfaceLinkEClass,
       source,
       new String[] {
         "Label", "RequiredInterfaceLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getRequiredInterfaceLink_Interface(),
       source,
       new String[] {
         "Label", "interface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceAllocationEClass,
       source,
       new String[] {
         "Label", "InterfaceRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deployableElementEClass,
       source,
       new String[] {
         "Label", "DeployableElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeployableElement_DeployingLinks(),
       source,
       new String[] {
         "Label", "deployingLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deploymentTargetEClass,
       source,
       new String[] {
         "Label", "DeploymentTarget" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeploymentTarget_DeploymentLinks(),
       source,
       new String[] {
         "Label", "deployments" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractDeploymentLinkEClass,
       source,
       new String[] {
         "Label", "AbstractDeployement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_DeployedElement(),
       source,
       new String[] {
         "Label", "deployedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_Location(),
       source,
       new String[] {
         "Label", "location" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalLinkEndEClass,
       source,
       new String[] {
         "Label", "PhysicalLinkEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Port(),
       source,
       new String[] {
         "Label", "port" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Part(),
       source,
       new String[] {
         "Label", "part" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "Label", "ownedFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
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
      (blockArchitectureEClass,
       source,
       new String[] {
         "metaclass", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedInterfacePkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_OwnedDataPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (blockEClass,
       source,
       new String[] {
         "metaclass", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedInterfacePkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlock_OwnedDataPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentArchitectureEClass,
       source,
       new String[] {
         "metaclass", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentEClass,
       source,
       new String[] {
         "metaclass", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_OwnedInterfaceUses(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaceLinks(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaceLinks(),
       source,
       new String[] {
         "featureName", "interfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "BehavioredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (partEClass,
       source,
       new String[] {
         "metaclass", "Property", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalPart" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (architectureAllocationEClass,
       source,
       new String[] {
         "metaclass", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfacePkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.InterfacePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfaces(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfacePkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceEClass,
       source,
       new String[] {
         "metaclass", "Interface", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Interface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_Mechanism(),
       source,
       new String[] {
         "featureName", "mechanism", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.Interface", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceImplementations(),
       source,
       new String[] {
         "umlOppositeReference", "contract", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "InterfaceRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceUses(),
       source,
       new String[] {
         "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceImplementationEClass,
       source,
       new String[] {
         "metaclass", "InterfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.InterfaceImplementation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_InterfaceImplementor(),
       source,
       new String[] {
         "featureName", "implementingClassifier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InterfaceRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_ImplementedInterface(),
       source,
       new String[] {
         "featureName", "contract", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InterfaceRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceUseEClass,
       source,
       new String[] {
         "metaclass", "Usage", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.InterfaceUse" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_InterfaceUser(),
       source,
       new String[] {
         "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_UsedInterface(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (providedInterfaceLinkEClass,
       source,
       new String[] {
         "metaclass", "InterfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.ProvidedInterfaceLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProvidedInterfaceLink_Interface(),
       source,
       new String[] {
         "featureName", "contract", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InterfaceRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (requiredInterfaceLinkEClass,
       source,
       new String[] {
         "metaclass", "Usage", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.RequiredInterfaceLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getRequiredInterfaceLink_Interface(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interfaceAllocationEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.InterfaceRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deployableElementEClass,
       source,
       new String[] {
         "metaclass", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeployableElement_DeployingLinks(),
       source,
       new String[] {
         "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (deploymentTargetEClass,
       source,
       new String[] {
         "metaclass", "Namespace" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeploymentTarget_DeploymentLinks(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractDeploymentLinkEClass,
       source,
       new String[] {
         "metaclass", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_DeployedElement(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractDeploymentLink_Location(),
       source,
       new String[] {
         "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Port(),
       source,
       new String[] {
         "featureName", "role", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ConnectorEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLinkEnd_Part(),
       source,
       new String[] {
         "featureName", "partWithPort", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ConnectorEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchanges(),
       source,
       new String[] {
         "featureName", "ownedConnector", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPkg_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "featureName", "ownedConnector", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getBlockArchitecture_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlockArchitecture_OwnedInterfacePkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlockArchitecture_OwnedDataPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedAbstractCapabilityPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedInterfacePkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getBlock_OwnedDataPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_OwnedInterfaceUses(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_UsedInterfaceLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_UsedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_ImplementedInterfaceLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_ImplementedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_ProvidedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponent_RequiredInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getPart_ProvidedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getPart_RequiredInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfacePkg_OwnedInterfacePkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_ImplementorComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_UserComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_InterfaceImplementations(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterface_InterfaceUses(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfaceImplementation_InterfaceImplementor(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfaceImplementation_ImplementedInterface(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfaceUse_InterfaceUser(),
       source,
       new String[] {
       });
    addAnnotation
      (getInterfaceUse_UsedInterface(),
       source,
       new String[] {
       });
    addAnnotation
      (getProvidedInterfaceLink_Interface(),
       source,
       new String[] {
       });
    addAnnotation
      (getRequiredInterfaceLink_Interface(),
       source,
       new String[] {
       });
    addAnnotation
      (getDeployableElement_DeployingLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getDeploymentTarget_DeploymentLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractDeploymentLink_DeployedElement(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractDeploymentLink_Location(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLinkEnd_Port(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalLinkEnd_Part(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPkg_OwnedFunctionalLinks(),
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
      (getBlockArchitecture_ProvisionedArchitectureAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_ProvisioningArchitectureAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_AllocatedArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedArchitectureAllocations.allocatedArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_AllocatingArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisioningArchitectureAllocations.allocatingArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBlockArchitecture_System(),
       source,
       new String[] {
         "viatra.variant", "freeform", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "pattern BlockArchitecture__system(self : BlockArchitecture, target : Component) {\r\n\tfind SystemAnalysis__system(self, target);\r\n} or {\r\n\tfind LogicalArchitecture__system(self, target);\r\n} or {\r\n\tfind PhysicalArchitecture__system(self, target);\r\n} or {\r\n\tfind EPBSArchitecture__system(self, target);\r\n}\r\n\r\npattern SystemAnalysis__system(self : SystemAnalysis, target : SystemComponent) {\r\n\tSystemAnalysis.ownedSystemComponentPkg(self, pckg);\r\n\tSystemComponentPkg.ownedSystemComponents(pckg, target);\r\n\tComponent.actor(target, false);\r\n}\r\n\r\npattern LogicalArchitecture__system(self : LogicalArchitecture, target : LogicalComponent) {\r\n\tLogicalArchitecture.ownedLogicalComponentPkg(self, pckg);\r\n\tLogicalComponentPkg.ownedLogicalComponents(pckg, target);\r\n\tComponent.actor(target, false);\r\n}\r\n\r\npattern PhysicalArchitecture__system(self : PhysicalArchitecture, target : PhysicalComponent) {\r\n\tPhysicalArchitecture.ownedPhysicalComponentPkg(self, pckg);\r\n\tPhysicalComponentPkg.ownedPhysicalComponents(pckg, target);\r\n\tComponent.actor(target, false);\r\n}\r\n\r\npattern EPBSArchitecture__system(self : EPBSArchitecture, target : ConfigurationItem) {\r\n\tEPBSArchitecture.ownedConfigurationItemPkg(self, pckg);\r\n\tConfigurationItemPkg.ownedConfigurationItems(pckg, target);\r\n\tComponent.actor(target, false);\r\n}" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaceLinks(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedInterfaceUses" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_UsedInterfaces(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "usedInterfaceLinks.usedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaceLinks(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedInterfaceImplementations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ImplementedInterfaces(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "implementedInterfaceLinks.implementedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RealizedComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Component.outgoingTraces(self, outgoingTraces);\r\nComponentRealization.realizedComponent(outgoingTraces, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RealizingComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Component.incomingTraces(self, incomingTraces);\r\nComponentRealization.realizingComponent(incomingTraces, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ProvidedInterfaces(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "containedComponentPorts.providedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RequiredInterfaces(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "containedComponentPorts.requiredInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedParts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_ContainedPhysicalPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponent_RepresentingParts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "typedElements" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_ProvidedInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Part.type(self, component);\r\nComponent.providedInterfaces(component, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_RequiredInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Part.type(self, component);\r\nComponent.requiredInterfaces(component, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_DeployedParts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "deploymentLinks.deployedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPart_DeployingParts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "deployingLinks.location" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getArchitectureAllocation_AllocatedArchitecture(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getArchitectureAllocation_AllocatingArchitecture(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentRealization_RealizedComponent(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentRealization_RealizingComponent(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ImplementorComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "interfaceImplementations.interfaceImplementor" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_UserComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "interfaceUses.interfaceUser" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceImplementations(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "implementedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_InterfaceUses(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "usedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvisioningInterfaceAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_AllocatingInterfaces(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisioningInterfaceAllocations.allocatingInterfaceAllocator" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_AllocatingComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisioningInterfaceAllocations.allocatingInterfaceAllocator" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ExchangeItems(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedExchangeItemAllocations.allocatedItem" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RequiringComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Interface.requiringComponentPorts(self, port);\r\nComponent.containedComponentPorts(target, port);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RequiringComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "requiredInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvidingComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Interface.providingComponentPorts(self, port);\r\nComponent.containedComponentPorts(target, port);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_ProvidingComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "providedInterfaces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizingLogicalInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ContextInterfaceRealization.targetElement(cir, self); \r\nContextInterfaceRealization.sourceElement(cir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizedContextInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ContextInterfaceRealization.sourceElement(cir, self);\r\nContextInterfaceRealization.targetElement(cir, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizingPhysicalInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "LogicalInterfaceRealization.targetElement(cir, self); \r\nLogicalInterfaceRealization.sourceElement(cir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterface_RealizedLogicalInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "LogicalInterfaceRealization.sourceElement(cir, self);\r\nLogicalInterfaceRealization.targetElement(cir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceImplementation_InterfaceImplementor(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedInterfaceImplementations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceUse_InterfaceUser(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedInterfaceUses" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocation_AllocatedInterface(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocation_AllocatingInterfaceAllocator(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_ProvisionedInterfaceAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInterfaceAllocator_AllocatedInterfaces(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedInterfaceAllocations.allocatedInterface" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemAllocation_AllocatingInterface(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedExchangeItemAllocations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeployableElement_DeployingLinks(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "deployedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDeploymentTarget_DeploymentLinks(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "location" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractPhysicalArtifact_AllocatorConfigurationItems(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalArtifactRealization.targetElement(par, self);\r\nPhysicalArtifactRealization.allocatingComponent(par, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractPhysicalLinkEnd_InvolvedLinks(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalLink.linkEnds(target, self);\r\n} or {\r\n\tPhysicalLinkEnd.port(ple, self);\t\r\n\tPhysicalLink.linkEnds(target, ple);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_Categories(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "links" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_SourcePhysicalPort(),
       source,
       new String[] {
         "viatra.variant", "unimplemented", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Unable to match on a positional criteria linkEnds[0] " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_TargetPhysicalPort(),
       source,
       new String[] {
         "viatra.variant", "unimplemented", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Unable to match on a positional criteria linkEnds[1] " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_RealizedPhysicalLinks(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalLinkRealization.sourceElement(plr, self);\r\nPhysicalLinkRealization.targetElement(plr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalLink_RealizingPhysicalLinks(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalLinkRealization.targetElement(plr, self);\r\nPhysicalLinkRealization.sourceElement(plr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_FirstPhysicalPathInvolvements(),
       source,
       new String[] {
         "viatra.variant", "freeform", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "pattern PhysicalPath__firstPhysicalPathInvolvements(self : PhysicalPath, target : PhysicalPathInvolvement) {\r\n\tPhysicalPath.ownedPhysicalPathInvolvements(self, target);\r\n\tPhysicalPathInvolvement.involved(target, _);\r\n\tneg find _PreviousInvolvement(target, _);\r\n}\r\nprivate pattern _PreviousInvolvement(ppi : PhysicalPathInvolvement, previous : PhysicalPathInvolvement) {\r\n\tPhysicalPathInvolvement.previousInvolvements(ppi, previous);\r\n}\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_RealizedPhysicalPaths(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalPathRealization.sourceElement(ppr, self);\r\nPhysicalPathRealization.targetElement(ppr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPath_RealizingPhysicalPaths(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalPathRealization.targetElement(ppr, self);\r\nPhysicalPathRealization.sourceElement(ppr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_PreviousInvolvements(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalPathInvolvement.nextInvolvements(target, self);\r\n// TODO understand why we should verify that target is in the same path than self ...\r\nPhysicalPath.ownedPhysicalPathInvolvements(pp, self);\r\nPhysicalPath.ownedPhysicalPathInvolvements(pp, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_InvolvedElement(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathInvolvement_InvolvedComponent(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalPathInvolvement.involved(self, part);\r\nPart.type(part, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPathReference_ReferencedPhysicalPath(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_AllocatedComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentPortAllocation.allocatingPort(ppr, self);\r\nComponentPortAllocation.allocatedPort(ppr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_RealizedPhysicalPorts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalPortRealization.sourceElement(ppr, self);\r\nPhysicalPortRealization.targetElement(ppr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalPort_RealizingPhysicalPorts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalPortRealization.targetElement(ppr, self);\r\nPhysicalPortRealization.sourceElement(ppr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createIgnoreAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"; //$NON-NLS-1$
    addAnnotation
      (architectureAllocationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (interfaceImplementationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (interfaceUseEClass,
       source,
       new String[] {
       });
    addAnnotation
      (providedInterfaceLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (requiredInterfaceLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (interfaceAllocationEClass,
       source,
       new String[] {
       });
  }

} //CsPackageImpl
