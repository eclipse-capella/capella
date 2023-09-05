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
package org.polarsys.capella.core.data.pa.impl;

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
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentKind;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalNode;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl;


import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PaPackageImpl extends EPackageImpl implements PaPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalArchitecturePkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalArchitectureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalFunctionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalFunctionPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalComponentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalComponentPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalNodeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalArchitectureRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalInterfaceRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum physicalComponentKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum physicalComponentNatureEEnum = null;

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
   * @see org.polarsys.capella.core.data.pa.PaPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private PaPackageImpl() {
    super(eNS_URI, PaFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link PaPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static PaPackage init() {
    if (isInited) return (PaPackage)EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredPaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    PaPackageImpl thePaPackage = registeredPaPackage instanceof PaPackageImpl ? (PaPackageImpl)registeredPaPackage : new PaPackageImpl();

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
    thePaPackage.createPackageContents();
    theCapellamodellerPackage.createPackageContents();
    theCapellacorePackage.createPackageContents();
    theOaPackage.createPackageContents();
    theCtxPackage.createPackageContents();
    theLaPackage.createPackageContents();
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
    thePaPackage.initializePackageContents();
    theCapellamodellerPackage.initializePackageContents();
    theCapellacorePackage.initializePackageContents();
    theOaPackage.initializePackageContents();
    theCtxPackage.initializePackageContents();
    theLaPackage.initializePackageContents();
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
    thePaPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(PaPackage.eNS_URI, thePaPackage);
    return thePaPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalArchitecturePkg() {
    return physicalArchitecturePkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs() {
    return (EReference)physicalArchitecturePkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecturePkg_OwnedPhysicalArchitectures() {
    return (EReference)physicalArchitecturePkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalArchitecture() {
    return physicalArchitectureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_OwnedPhysicalComponentPkg() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_ContainedCapabilityRealizationPkg() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_ContainedPhysicalFunctionPkg() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_OwnedDeployments() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_OwnedLogicalArchitectureRealizations() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_AllocatedLogicalArchitectures() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalArchitecture_AllocatingEpbsArchitectures() {
    return (EReference)physicalArchitectureEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalFunction() {
    return physicalFunctionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunction_OwnedPhysicalFunctionPkgs() {
    return (EReference)physicalFunctionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunction_AllocatingPhysicalComponents() {
    return (EReference)physicalFunctionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunction_RealizedLogicalFunctions() {
    return (EReference)physicalFunctionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunction_ContainedPhysicalFunctions() {
    return (EReference)physicalFunctionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunction_ChildrenPhysicalFunctions() {
    return (EReference)physicalFunctionEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalFunctionPkg() {
    return physicalFunctionPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunctionPkg_OwnedPhysicalFunctions() {
    return (EReference)physicalFunctionPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs() {
    return (EReference)physicalFunctionPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalComponent() {
    return physicalComponentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getPhysicalComponent_Kind() {
    return (EAttribute)physicalComponentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getPhysicalComponent_Nature() {
    return (EAttribute)physicalComponentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_OwnedDeploymentLinks() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_OwnedPhysicalComponents() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_OwnedPhysicalComponentPkgs() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_LogicalInterfaceRealizations() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_SubPhysicalComponents() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_RealizedLogicalComponents() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_AllocatedPhysicalFunctions() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_DeployedPhysicalComponents() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponent_DeployingPhysicalComponents() {
    return (EReference)physicalComponentEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalComponentPkg() {
    return physicalComponentPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponentPkg_OwnedPhysicalComponents() {
    return (EReference)physicalComponentPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponentPkg_OwnedPhysicalComponentPkgs() {
    return (EReference)physicalComponentPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponentPkg_OwnedKeyParts() {
    return (EReference)physicalComponentPkgEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalComponentPkg_OwnedDeployments() {
    return (EReference)physicalComponentPkgEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalNode() {
    return physicalNodeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalNode_SubPhysicalNodes() {
    return (EReference)physicalNodeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalArchitectureRealization() {
    return logicalArchitectureRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalInterfaceRealization() {
    return logicalInterfaceRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getPhysicalComponentKind() {
    return physicalComponentKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getPhysicalComponentNature() {
    return physicalComponentNatureEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PaFactory getPaFactory() {
    return (PaFactory)getEFactoryInstance();
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
    physicalArchitecturePkgEClass = createEClass(PHYSICAL_ARCHITECTURE_PKG);
    createEReference(physicalArchitecturePkgEClass, PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURE_PKGS);
    createEReference(physicalArchitecturePkgEClass, PHYSICAL_ARCHITECTURE_PKG__OWNED_PHYSICAL_ARCHITECTURES);

    physicalArchitectureEClass = createEClass(PHYSICAL_ARCHITECTURE);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES);
    createEReference(physicalArchitectureEClass, PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES);

    physicalFunctionEClass = createEClass(PHYSICAL_FUNCTION);
    createEReference(physicalFunctionEClass, PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS);
    createEReference(physicalFunctionEClass, PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS);
    createEReference(physicalFunctionEClass, PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS);
    createEReference(physicalFunctionEClass, PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS);
    createEReference(physicalFunctionEClass, PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS);

    physicalFunctionPkgEClass = createEClass(PHYSICAL_FUNCTION_PKG);
    createEReference(physicalFunctionPkgEClass, PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS);
    createEReference(physicalFunctionPkgEClass, PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTION_PKGS);

    physicalComponentEClass = createEClass(PHYSICAL_COMPONENT);
    createEAttribute(physicalComponentEClass, PHYSICAL_COMPONENT__KIND);
    createEAttribute(physicalComponentEClass, PHYSICAL_COMPONENT__NATURE);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS);
    createEReference(physicalComponentEClass, PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS);

    physicalComponentPkgEClass = createEClass(PHYSICAL_COMPONENT_PKG);
    createEReference(physicalComponentPkgEClass, PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS);
    createEReference(physicalComponentPkgEClass, PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS);
    createEReference(physicalComponentPkgEClass, PHYSICAL_COMPONENT_PKG__OWNED_KEY_PARTS);
    createEReference(physicalComponentPkgEClass, PHYSICAL_COMPONENT_PKG__OWNED_DEPLOYMENTS);

    physicalNodeEClass = createEClass(PHYSICAL_NODE);
    createEReference(physicalNodeEClass, PHYSICAL_NODE__SUB_PHYSICAL_NODES);

    logicalArchitectureRealizationEClass = createEClass(LOGICAL_ARCHITECTURE_REALIZATION);

    logicalInterfaceRealizationEClass = createEClass(LOGICAL_INTERFACE_REALIZATION);

    // Create enums
    physicalComponentKindEEnum = createEEnum(PHYSICAL_COMPONENT_KIND);
    physicalComponentNatureEEnum = createEEnum(PHYSICAL_COMPONENT_NATURE);
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
    DeploymentPackage theDeploymentPackage = (DeploymentPackage)EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);
    CsPackage theCsPackage = (CsPackage)EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    LaPackage theLaPackage = (LaPackage)EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    EpbsPackage theEpbsPackage = (EpbsPackage)EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI);
    FaPackage theFaPackage = (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);

    // Add subpackages
    getESubpackages().add(theDeploymentPackage);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    physicalArchitecturePkgEClass.getESuperTypes().add(theCsPackage.getBlockArchitecturePkg());
    physicalArchitectureEClass.getESuperTypes().add(theCsPackage.getComponentArchitecture());
    physicalFunctionEClass.getESuperTypes().add(theFaPackage.getAbstractFunction());
    physicalFunctionPkgEClass.getESuperTypes().add(theFaPackage.getFunctionPkg());
    physicalComponentEClass.getESuperTypes().add(theCsPackage.getAbstractPhysicalArtifact());
    physicalComponentEClass.getESuperTypes().add(theCsPackage.getComponent());
    physicalComponentEClass.getESuperTypes().add(theCapellacommonPackage.getCapabilityRealizationInvolvedElement());
    physicalComponentEClass.getESuperTypes().add(theCsPackage.getDeployableElement());
    physicalComponentEClass.getESuperTypes().add(theCsPackage.getDeploymentTarget());
    physicalComponentPkgEClass.getESuperTypes().add(theCsPackage.getComponentPkg());
    physicalComponentPkgEClass.getESuperTypes().add(theInformationPackage.getAssociationPkg());
    physicalNodeEClass.getESuperTypes().add(this.getPhysicalComponent());
    logicalArchitectureRealizationEClass.getESuperTypes().add(theCsPackage.getArchitectureAllocation());
    logicalInterfaceRealizationEClass.getESuperTypes().add(theCsPackage.getInterfaceAllocation());

    // Initialize classes and features; add operations and parameters
    initEClass(physicalArchitecturePkgEClass, PhysicalArchitecturePkg.class, "PhysicalArchitecturePkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs(), this.getPhysicalArchitecturePkg(), null, "ownedPhysicalArchitecturePkgs", null, 0, -1, PhysicalArchitecturePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecturePkg_OwnedPhysicalArchitectures(), this.getPhysicalArchitecture(), null, "ownedPhysicalArchitectures", null, 0, -1, PhysicalArchitecturePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalArchitectureEClass, PhysicalArchitecture.class, "PhysicalArchitecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_OwnedPhysicalComponentPkg(), this.getPhysicalComponentPkg(), null, "ownedPhysicalComponentPkg", null, 0, 1, PhysicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_ContainedCapabilityRealizationPkg(), theLaPackage.getCapabilityRealizationPkg(), null, "containedCapabilityRealizationPkg", null, 0, 1, PhysicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_ContainedPhysicalFunctionPkg(), this.getPhysicalFunctionPkg(), null, "containedPhysicalFunctionPkg", null, 0, 1, PhysicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_OwnedDeployments(), theCsPackage.getAbstractDeploymentLink(), null, "ownedDeployments", null, 0, -1, PhysicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_OwnedLogicalArchitectureRealizations(), this.getLogicalArchitectureRealization(), null, "ownedLogicalArchitectureRealizations", null, 0, -1, PhysicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(), this.getLogicalArchitectureRealization(), null, "allocatedLogicalArchitectureRealizations", null, 0, -1, PhysicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_AllocatedLogicalArchitectures(), theLaPackage.getLogicalArchitecture(), theLaPackage.getLogicalArchitecture_AllocatingPhysicalArchitectures(), "allocatedLogicalArchitectures", null, 0, -1, PhysicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalArchitecture_AllocatingEpbsArchitectures(), theEpbsPackage.getEPBSArchitecture(), theEpbsPackage.getEPBSArchitecture_AllocatedPhysicalArchitectures(), "allocatingEpbsArchitectures", null, 0, -1, PhysicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalFunctionEClass, PhysicalFunction.class, "PhysicalFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalFunction_OwnedPhysicalFunctionPkgs(), this.getPhysicalFunctionPkg(), null, "ownedPhysicalFunctionPkgs", null, 0, -1, PhysicalFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalFunction_AllocatingPhysicalComponents(), this.getPhysicalComponent(), this.getPhysicalComponent_AllocatedPhysicalFunctions(), "allocatingPhysicalComponents", null, 0, -1, PhysicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalFunction_RealizedLogicalFunctions(), theLaPackage.getLogicalFunction(), theLaPackage.getLogicalFunction_RealizingPhysicalFunctions(), "realizedLogicalFunctions", null, 0, -1, PhysicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalFunction_ContainedPhysicalFunctions(), this.getPhysicalFunction(), null, "containedPhysicalFunctions", null, 0, -1, PhysicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalFunction_ChildrenPhysicalFunctions(), this.getPhysicalFunction(), null, "childrenPhysicalFunctions", null, 0, -1, PhysicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalFunctionPkgEClass, PhysicalFunctionPkg.class, "PhysicalFunctionPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalFunctionPkg_OwnedPhysicalFunctions(), this.getPhysicalFunction(), null, "ownedPhysicalFunctions", null, 0, -1, PhysicalFunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs(), this.getPhysicalFunctionPkg(), null, "ownedPhysicalFunctionPkgs", null, 0, -1, PhysicalFunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalComponentEClass, PhysicalComponent.class, "PhysicalComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getPhysicalComponent_Kind(), this.getPhysicalComponentKind(), "kind", null, 0, 1, PhysicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getPhysicalComponent_Nature(), this.getPhysicalComponentNature(), "nature", null, 0, 1, PhysicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_OwnedDeploymentLinks(), theCsPackage.getAbstractDeploymentLink(), null, "ownedDeploymentLinks", null, 0, -1, PhysicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_OwnedPhysicalComponents(), this.getPhysicalComponent(), null, "ownedPhysicalComponents", null, 0, -1, PhysicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_OwnedPhysicalComponentPkgs(), this.getPhysicalComponentPkg(), null, "ownedPhysicalComponentPkgs", null, 0, -1, PhysicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_LogicalInterfaceRealizations(), this.getLogicalInterfaceRealization(), null, "logicalInterfaceRealizations", null, 0, -1, PhysicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_SubPhysicalComponents(), this.getPhysicalComponent(), null, "subPhysicalComponents", null, 0, -1, PhysicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_RealizedLogicalComponents(), theLaPackage.getLogicalComponent(), theLaPackage.getLogicalComponent_RealizingPhysicalComponents(), "realizedLogicalComponents", null, 0, -1, PhysicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_AllocatedPhysicalFunctions(), this.getPhysicalFunction(), this.getPhysicalFunction_AllocatingPhysicalComponents(), "allocatedPhysicalFunctions", null, 0, -1, PhysicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_DeployedPhysicalComponents(), this.getPhysicalComponent(), null, "deployedPhysicalComponents", null, 0, -1, PhysicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponent_DeployingPhysicalComponents(), this.getPhysicalComponent(), null, "deployingPhysicalComponents", null, 0, -1, PhysicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalComponentPkgEClass, PhysicalComponentPkg.class, "PhysicalComponentPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalComponentPkg_OwnedPhysicalComponents(), this.getPhysicalComponent(), null, "ownedPhysicalComponents", null, 0, -1, PhysicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(), this.getPhysicalComponentPkg(), null, "ownedPhysicalComponentPkgs", null, 0, -1, PhysicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponentPkg_OwnedKeyParts(), theInformationPackage.getKeyPart(), null, "ownedKeyParts", null, 0, -1, PhysicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPhysicalComponentPkg_OwnedDeployments(), theCsPackage.getAbstractDeploymentLink(), null, "ownedDeployments", null, 0, -1, PhysicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalNodeEClass, PhysicalNode.class, "PhysicalNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalNode_SubPhysicalNodes(), this.getPhysicalNode(), null, "subPhysicalNodes", null, 0, -1, PhysicalNode.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(logicalArchitectureRealizationEClass, LogicalArchitectureRealization.class, "LogicalArchitectureRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(logicalInterfaceRealizationEClass, LogicalInterfaceRealization.class, "LogicalInterfaceRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(physicalComponentKindEEnum, PhysicalComponentKind.class, "PhysicalComponentKind"); //$NON-NLS-1$
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.UNSET);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.HARDWARE);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.HARDWARE_COMPUTER);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.SOFTWARE);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.SOFTWARE_DEPLOYMENT_UNIT);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.SOFTWARE_EXECUTION_UNIT);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.SOFTWARE_APPLICATION);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.FIRMWARE);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.PERSON);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.FACILITIES);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.DATA);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.MATERIALS);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.SERVICES);
    addEEnumLiteral(physicalComponentKindEEnum, PhysicalComponentKind.PROCESSES);

    initEEnum(physicalComponentNatureEEnum, PhysicalComponentNature.class, "PhysicalComponentNature"); //$NON-NLS-1$
    addEEnumLiteral(physicalComponentNatureEEnum, PhysicalComponentNature.UNSET);
    addEEnumLiteral(physicalComponentNatureEEnum, PhysicalComponentNature.BEHAVIOR);
    addEEnumLiteral(physicalComponentNatureEEnum, PhysicalComponentNature.NODE);

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
         "description", "PhysicalArchitecture aims at defining the system\'s software, middleware and hardware architecture modelling language (close to the OMG\'s Platform Independent Model (PIM) in addition to OMG\'s Platform Model (PM)) using notions close to OMG\'s MARTE Resource concept. It adds the Deployment concern.\r\nThis concern aggregates a lot of concepts regarding the others. A re-engineering of this concern should make sense.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CompositeStructure.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitecturePkgEClass,
       source,
       new String[] {
         "description", "container for physical architecture elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs(),
       source,
       new String[] {
         "description", "sub-(physical architecture) packages contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitectures(),
       source,
       new String[] {
         "description", "the physical architecture elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitectureEClass,
       source,
       new String[] {
         "description", "Model describing physical architecture part - hardware components & related items -  associated to (created during) a modelling phase", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedPhysicalComponentPkg(),
       source,
       new String[] {
         "description", "a package containing the physical components involved in this physical architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedDeployments(),
       source,
       new String[] {
         "description", "the various deployments associated with this physical architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "description", "the list of a relationships between physical architectures and the logical architectures that they realize, stored/owned by this architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "description", "the list of relationships between this physical architecture and the logical architectures to which it is allocated\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalFunctionEClass,
       source,
       new String[] {
         "description", "Function applied at physical level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this element is used in the \"functional approach\" usage, as the result of the flow down/refinement of the functions at the logical architecture level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_physical_functions.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_OwnedPhysicalFunctionPkgs(),
       source,
       new String[] {
         "description", "the sub-(physical function) packages contained in this physical function", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_ChildrenPhysicalFunctions(),
       source,
       new String[] {
         "description", "list of children physical functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalFunctionPkgEClass,
       source,
       new String[] {
         "description", "container for physical functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "Used to structure the storage of physical function elements inside a physical architecture\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunctionPkg_OwnedPhysicalFunctions(),
       source,
       new String[] {
         "description", "the physical functions contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs(),
       source,
       new String[] {
         "description", "the sub-(physical function) packages contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentEClass,
       source,
       new String[] {
         "description", "Physical Components are the artifacts enabling to describe architectural solutions to satisfy the logical architecture identified at the upper abstraction level. Physical components are identified according to physical rationals (i.e. components reuse, available COTS, non functional constraints...)\r\nExamples: Software component, executable, hardware component (mechanical devices, electronical boards, equipments)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "refer to description", //$NON-NLS-1$ //$NON-NLS-2$
         "arcadia_description", "Physical Components are the artefacts enabling to describe the final physical decomposition of the system. Physical components are identified according to physical and development constraints.\r\nTwo kinds of physical components exist: behavioural and implementation components.\r\nTwo kinds of physical components are identified:\r\n- A behavioural component is a physical component in charge of implementing / realising part of the functions allocated to the system\r\nExample: software component, VHDL program (for a programmable device), hardware selector...\r\n- An implementation component  is a material physical component, resource embedding some behavioural components, and necessary to their expected behaviour.\r\nExample: Hardware computing board, computer, FPGA (programmable device), ...\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_physical_components.png", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_Kind(),
       source,
       new String[] {
         "description", "specifies the type of physical component (refer to PhysicalComponentKind for detailed description)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to PhysicalComponentKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_Nature(),
       source,
       new String[] {
         "description", "specifies the nature of this physical component, typically whether it is an actual execution node, or a behavioral component like a SW part\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to PhysicalComponentNature definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_OwnedDeploymentLinks(),
       source,
       new String[] {
         "description", "the various deployments of this physical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_OwnedPhysicalComponents(),
       source,
       new String[] {
         "description", "the physical components stored under this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
         "description", "the sub-(physical component) packages owned by this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_LogicalInterfaceRealizations(),
       source,
       new String[] {
         "description", "the list of logical interfaces that this physical component reallizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_SubPhysicalComponents(),
       source,
       new String[] {
         "description", "the children components of this physical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_RealizedLogicalComponents(),
       source,
       new String[] {
         "description", "(automatically computed) the list of realizations links coming from logical components, and in which this physical component is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentPkgEClass,
       source,
       new String[] {
         "description", "a container for physical component entities\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponents(),
       source,
       new String[] {
         "description", "the physical components stored in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
         "description", "the sub-(physical component) packages contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedKeyParts(),
       source,
       new String[] {
         "description", "the key parts contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedDeployments(),
       source,
       new String[] {
         "description", "the physical deployment definitions stored in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalNodeEClass,
       source,
       new String[] {
         "description", "a physical resource hosting behavioral components, and required for their execution or expected behavior\r\n[source: Arcadia encyclopedia]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalNode_SubPhysicalNodes(),
       source,
       new String[] {
         "description", "all derived children of this physical node\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum,
       source,
       new String[] {
         "description", "allows to categorize a physical component, with respect to real life physical entities\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the physical component kind is not precised\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the physical component is a hardware resource\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the physical component is a computing resource\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "used when the physical component is a software entity\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "used when the physical component is a software deployment unit\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "used when the physical component is a software execution unit\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "used when the physical component is a software application\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "description", "used when the physical component is a firmware part\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "description", "used when the physical component is a person\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "description", "used when the physical component refers to Facilities\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "description", "used when the physical component represents a set of data\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(11),
       source,
       new String[] {
         "description", "used when the physical component represents a bunch of materials\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(12),
       source,
       new String[] {
         "description", "used when the physical components represents a set of services\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(13),
       source,
       new String[] {
         "description", "used when the physical component represents a set of processes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitectureRealizationEClass,
       source,
       new String[] {
         "description", "mediator class supporting the implementation of the allocation link between a physical architecture, and the logical architecture(s) that it realizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalInterfaceRealizationEClass,
       source,
       new String[] {
         "description", "mediator class supporting the implementation of the allocation link between a physical interface, and the logical interface(s) that it realizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum,
       source,
       new String[] {
         "description", "characterizes a physical component, with respect to its property of being a host of behavioral components, or a behavioral component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the physical component nature is not precised\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the physical component nature is behavioral (typically, a piece of software)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the physical component is a host for behavioral components (typically, a computing resource)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
      (physicalArchitectureEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedPhysicalComponentPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "feature", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_ContainedPhysicalFunctionPkg(),
       source,
       new String[] {
         "feature", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedDeployments(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectures(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatingEpbsArchitectures(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalFunctionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalFunction_OwnedPhysicalFunctionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalFunction_AllocatingPhysicalComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_RealizedLogicalFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalFunction_ContainedPhysicalFunctions(),
       source,
       new String[] {
         "feature", "ownedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_ChildrenPhysicalFunctions(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalFunctionPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalFunctionPkg_OwnedPhysicalFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalComponentEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_Nature(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_OwnedDeploymentLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_OwnedPhysicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_SubPhysicalComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_RealizedLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_AllocatedPhysicalFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_DeployedPhysicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_DeployingPhysicalComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedKeyParts(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalNodeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalNode_SubPhysicalNodes(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
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
      (physicalArchitecturePkgEClass,
       source,
       new String[] {
         "Label", "PhysicalArchitecturePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs(),
       source,
       new String[] {
         "Label", "subPhysicalArchitecturePkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitectures(),
       source,
       new String[] {
         "Label", "ownedPhysicalArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitectureEClass,
       source,
       new String[] {
         "Label", "Physical Architecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedPhysicalComponentPkg(),
       source,
       new String[] {
         "Label", "ownedComponentPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedDeployments(),
       source,
       new String[] {
         "Label", "ownedDeployments" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "Label", "allocatedLogicalArchitectureImplementations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentEClass,
       source,
       new String[] {
         "Label", "PhysicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_SubPhysicalComponents(),
       source,
       new String[] {
         "Label", "subActors" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentPkgEClass,
       source,
       new String[] {
         "Label", "PhysicalComponentPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponents(),
       source,
       new String[] {
         "Label", "ownedComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
         "Label", "subPhysicalComponentPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedKeyParts(),
       source,
       new String[] {
         "Label", "ownedKeyParts" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedDeployments(),
       source,
       new String[] {
         "Label", "ownedDeployments" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalNodeEClass,
       source,
       new String[] {
         "Label", "PhysicalNode" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalNode_SubPhysicalNodes(),
       source,
       new String[] {
         "Label", "subActors" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum,
       source,
       new String[] {
         "Label", "PhysicalComponentType" //$NON-NLS-1$ //$NON-NLS-2$
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
      (physicalArchitecturePkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalArchitecturePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitectures(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitectureEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedPhysicalComponentPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedDeployments(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentEClass,
       source,
       new String[] {
         "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_Kind(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.AbstractPhysicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalComponentPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponents(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedKeyParts(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedDeployments(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalNodeEClass,
       source,
       new String[] {
         "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalNode" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum,
       source,
       new String[] {
         "enum", "PhysicalComponentType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "HARDWARE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "HARDWARE_COMPUTER" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "enumLiteral", "SOFTWARE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "enumLiteral", "SOFTWARE_DEPLOYMENT_UNIT" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "enumLiteral", "SOFTWARE_EXECUTION_UNIT" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "enumLiteral", "SOFTWARE_APPLICATION" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "enumLiteral", "FIRMWARE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "enumLiteral", "PERSON" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "enumLiteral", "FACILITIES" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "enumLiteral", "DATA" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(11),
       source,
       new String[] {
         "enumLiteral", "MATERIALS" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(12),
       source,
       new String[] {
         "enumLiteral", "SERVICES" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(13),
       source,
       new String[] {
         "enumLiteral", "PROCESSES" //$NON-NLS-1$ //$NON-NLS-2$
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
      (physicalArchitecturePkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which PhysicalArchitecturePkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which PhysicalArchitecture stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalArchitectureEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedPhysicalComponentPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which PhysicalComponentPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_ContainedPhysicalFunctionPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedDeployments(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which AbstractDeployment stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which LogicalArchitectureRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatingEpbsArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalFunctionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Activity", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "All functions are mapped to (empty) activities", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_OwnedPhysicalFunctionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which PhysicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_AllocatingPhysicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_RealizedLogicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_ContainedPhysicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_ChildrenPhysicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalFunctionPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunctionPkg_OwnedPhysicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which PhysicalFunction stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which PhysicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Blocks::Block", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "cannot map to uml::Component, which is not part of UML4SysML", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_Nature(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_OwnedDeploymentLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Blocks::Block cannot contain AbstractDeployment\'s equivalent, hence we find the nearest available package to store them.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_OwnedPhysicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Class::nestedClassifier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "the nesting relation is just convenient to store sub-components under a component in the three, even though the hierachical relationship between componenets is not\r\nderived from this nesting : instead, it relies on the Parts present in the component, that are typed by the sub-components types.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Class::nestedClassifier elements on which PhysicalComponent stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Blocks::Block cannot contain packages, hence we find the nearest available package to store them.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_LogicalInterfaceRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_SubPhysicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_RealizedLogicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_AllocatedPhysicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_DeployedPhysicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_DeployingPhysicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which PhysicalComponent stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which PhysicalComponentPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedKeyParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which KeyPart stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedDeployments(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which AbstractDeployment stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalNodeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Node", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalNode_SubPhysicalNodes(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(11),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(12),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentKindEEnum.getELiterals().get(13),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitectureRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalInterfaceRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::InterfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalComponentNatureEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecturePkg_OwnedPhysicalArchitectures(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedPhysicalComponentPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_OwnedDeployments(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponent_SubPhysicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedPhysicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedKeyParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalComponentPkg_OwnedDeployments(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalNode_SubPhysicalNodes(),
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
      (getPhysicalArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_ContainedPhysicalFunctionPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations(),
       source,
       new String[] {
         "derive", "self.ownedPartitions.representedElement.oclIsKindOf(PhysicalComponent) -> oclAsType(PhysicalComponent)", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedArchitectureAllocations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatedLogicalArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalArchitecture_AllocatingEpbsArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatingArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_AllocatingPhysicalComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalFunction.incomingTraces(self, incomingTraces);\r\nComponentFunctionalAllocation.sourceElement(incomingTraces, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_RealizedLogicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outFunctionRealizations.allocatedFunction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_ContainedPhysicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalFunction_ChildrenPhysicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "subFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_LogicalInterfaceRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedInterfaceAllocations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_SubPhysicalComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedPartitions.type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_RealizedLogicalComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "PhysicalComponent.outgoingTraces(self, lcr);\r\n\tLogicalComponentRealization.targetElement(lcr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_AllocatedPhysicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_DeployedPhysicalComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Part.abstractType(part, self);\r\n\tPart.deploymentLinks.deployedElement(part, deployedPart);\r\n\tPart.abstractType(deployedPart, target);\r\n} or {\r\n\tPart.abstractType(part, self);\r\n\tPart.deploymentLinks.deployedElement(part, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalComponent_DeployingPhysicalComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Part.abstractType(part, self);\r\n\tPart.deployingLinks.location(part, deployedPart);\r\n\tPart.abstractType(deployedPart, target);\r\n} or {\r\n\tPart.abstractType(part, self);\r\n\tPart.deploymentLinks.deployedElement(part, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalNode_SubPhysicalNodes(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedPartitions.type" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //PaPackageImpl
