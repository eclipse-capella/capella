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
package org.polarsys.capella.core.data.la.impl;

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
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
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
public class LaPackageImpl extends EPackageImpl implements LaPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalArchitecturePkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalArchitectureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalFunctionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalFunctionPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalComponentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass logicalComponentPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass capabilityRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass capabilityRealizationPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemAnalysisRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass contextInterfaceRealizationEClass = null;

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
   * @see org.polarsys.capella.core.data.la.LaPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private LaPackageImpl() {
    super(eNS_URI, LaFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link LaPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static LaPackage init() {
    if (isInited) return (LaPackage)EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredLaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    LaPackageImpl theLaPackage = registeredLaPackage instanceof LaPackageImpl ? (LaPackageImpl)registeredLaPackage : new LaPackageImpl();

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
    theLaPackage.createPackageContents();
    theCapellamodellerPackage.createPackageContents();
    theCapellacorePackage.createPackageContents();
    theOaPackage.createPackageContents();
    theCtxPackage.createPackageContents();
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
    theLaPackage.initializePackageContents();
    theCapellamodellerPackage.initializePackageContents();
    theCapellacorePackage.initializePackageContents();
    theOaPackage.initializePackageContents();
    theCtxPackage.initializePackageContents();
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
    theLaPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(LaPackage.eNS_URI, theLaPackage);
    return theLaPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalArchitecturePkg() {
    return logicalArchitecturePkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecturePkg_OwnedLogicalArchitectures() {
    return (EReference)logicalArchitecturePkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalArchitecture() {
    return logicalArchitectureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_OwnedLogicalComponentPkg() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_ContainedCapabilityRealizationPkg() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_ContainedLogicalFunctionPkg() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_OwnedSystemAnalysisRealizations() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_AllocatedSystemAnalysisRealizations() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_AllocatedSystemAnalyses() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalArchitecture_AllocatingPhysicalArchitectures() {
    return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalFunction() {
    return logicalFunctionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunction_OwnedLogicalFunctionPkgs() {
    return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunction_AllocatingLogicalComponents() {
    return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunction_RealizedSystemFunctions() {
    return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunction_RealizingPhysicalFunctions() {
    return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunction_ContainedLogicalFunctions() {
    return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunction_ChildrenLogicalFunctions() {
    return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalFunctionPkg() {
    return logicalFunctionPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunctionPkg_OwnedLogicalFunctions() {
    return (EReference)logicalFunctionPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalFunctionPkg_OwnedLogicalFunctionPkgs() {
    return (EReference)logicalFunctionPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalComponent() {
    return logicalComponentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_OwnedLogicalComponents() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_OwnedLogicalArchitectures() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_OwnedLogicalComponentPkgs() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_SubLogicalComponents() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_AllocatedLogicalFunctions() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_RealizedSystemComponents() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponent_RealizingPhysicalComponents() {
    return (EReference)logicalComponentEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLogicalComponentPkg() {
    return logicalComponentPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponentPkg_OwnedLogicalComponents() {
    return (EReference)logicalComponentPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getLogicalComponentPkg_OwnedLogicalComponentPkgs() {
    return (EReference)logicalComponentPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCapabilityRealization() {
    return capabilityRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealization_OwnedCapabilityRealizationInvolvements() {
    return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealization_InvolvedComponents() {
    return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealization_RealizedCapabilities() {
    return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealization_RealizedCapabilityRealizations() {
    return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealization_RealizingCapabilityRealizations() {
    return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCapabilityRealizationPkg() {
    return capabilityRealizationPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealizationPkg_OwnedCapabilityRealizations() {
    return (EReference)capabilityRealizationPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs() {
    return (EReference)capabilityRealizationPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemAnalysisRealization() {
    return systemAnalysisRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getContextInterfaceRealization() {
    return contextInterfaceRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public LaFactory getLaFactory() {
    return (LaFactory)getEFactoryInstance();
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
    logicalArchitecturePkgEClass = createEClass(LOGICAL_ARCHITECTURE_PKG);
    createEReference(logicalArchitecturePkgEClass, LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES);

    logicalArchitectureEClass = createEClass(LOGICAL_ARCHITECTURE);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES);
    createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES);

    logicalFunctionEClass = createEClass(LOGICAL_FUNCTION);
    createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS);
    createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS);
    createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS);
    createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS);
    createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS);
    createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS);

    logicalFunctionPkgEClass = createEClass(LOGICAL_FUNCTION_PKG);
    createEReference(logicalFunctionPkgEClass, LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS);
    createEReference(logicalFunctionPkgEClass, LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS);

    logicalComponentEClass = createEClass(LOGICAL_COMPONENT);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS);
    createEReference(logicalComponentEClass, LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS);

    logicalComponentPkgEClass = createEClass(LOGICAL_COMPONENT_PKG);
    createEReference(logicalComponentPkgEClass, LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS);
    createEReference(logicalComponentPkgEClass, LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS);

    capabilityRealizationEClass = createEClass(CAPABILITY_REALIZATION);
    createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS);
    createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__INVOLVED_COMPONENTS);
    createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__REALIZED_CAPABILITIES);
    createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS);
    createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS);

    capabilityRealizationPkgEClass = createEClass(CAPABILITY_REALIZATION_PKG);
    createEReference(capabilityRealizationPkgEClass, CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS);
    createEReference(capabilityRealizationPkgEClass, CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS);

    systemAnalysisRealizationEClass = createEClass(SYSTEM_ANALYSIS_REALIZATION);

    contextInterfaceRealizationEClass = createEClass(CONTEXT_INTERFACE_REALIZATION);
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
    CtxPackage theCtxPackage = (CtxPackage)EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI);
    PaPackage thePaPackage = (PaPackage)EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI);
    FaPackage theFaPackage = (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    InteractionPackage theInteractionPackage = (InteractionPackage)EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    logicalArchitecturePkgEClass.getESuperTypes().add(theCsPackage.getBlockArchitecturePkg());
    logicalArchitectureEClass.getESuperTypes().add(theCsPackage.getComponentArchitecture());
    logicalFunctionEClass.getESuperTypes().add(theFaPackage.getAbstractFunction());
    logicalFunctionPkgEClass.getESuperTypes().add(theFaPackage.getFunctionPkg());
    logicalComponentEClass.getESuperTypes().add(theCsPackage.getComponent());
    logicalComponentEClass.getESuperTypes().add(theCapellacommonPackage.getCapabilityRealizationInvolvedElement());
    logicalComponentPkgEClass.getESuperTypes().add(theCsPackage.getComponentPkg());
    capabilityRealizationEClass.getESuperTypes().add(theInteractionPackage.getAbstractCapability());
    capabilityRealizationPkgEClass.getESuperTypes().add(theCapellacommonPackage.getAbstractCapabilityPkg());
    systemAnalysisRealizationEClass.getESuperTypes().add(theCsPackage.getArchitectureAllocation());
    contextInterfaceRealizationEClass.getESuperTypes().add(theCsPackage.getInterfaceAllocation());

    // Initialize classes and features; add operations and parameters
    initEClass(logicalArchitecturePkgEClass, LogicalArchitecturePkg.class, "LogicalArchitecturePkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLogicalArchitecturePkg_OwnedLogicalArchitectures(), this.getLogicalArchitecture(), null, "ownedLogicalArchitectures", null, 0, -1, LogicalArchitecturePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(logicalArchitectureEClass, LogicalArchitecture.class, "LogicalArchitecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_OwnedLogicalComponentPkg(), this.getLogicalComponentPkg(), null, "ownedLogicalComponentPkg", null, 0, 1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_ContainedCapabilityRealizationPkg(), this.getCapabilityRealizationPkg(), null, "containedCapabilityRealizationPkg", null, 0, 1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_ContainedLogicalFunctionPkg(), this.getLogicalFunctionPkg(), null, "containedLogicalFunctionPkg", null, 0, 1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_OwnedSystemAnalysisRealizations(), this.getSystemAnalysisRealization(), null, "ownedSystemAnalysisRealizations", null, 0, -1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_AllocatedSystemAnalysisRealizations(), this.getSystemAnalysisRealization(), null, "allocatedSystemAnalysisRealizations", null, 0, -1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_AllocatedSystemAnalyses(), theCtxPackage.getSystemAnalysis(), theCtxPackage.getSystemAnalysis_AllocatingLogicalArchitectures(), "allocatedSystemAnalyses", null, 0, -1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalArchitecture_AllocatingPhysicalArchitectures(), thePaPackage.getPhysicalArchitecture(), thePaPackage.getPhysicalArchitecture_AllocatedLogicalArchitectures(), "allocatingPhysicalArchitectures", null, 0, -1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(logicalFunctionEClass, LogicalFunction.class, "LogicalFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLogicalFunction_OwnedLogicalFunctionPkgs(), this.getLogicalFunctionPkg(), null, "ownedLogicalFunctionPkgs", null, 0, -1, LogicalFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalFunction_AllocatingLogicalComponents(), this.getLogicalComponent(), this.getLogicalComponent_AllocatedLogicalFunctions(), "allocatingLogicalComponents", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalFunction_RealizedSystemFunctions(), theCtxPackage.getSystemFunction(), theCtxPackage.getSystemFunction_RealizingLogicalFunctions(), "realizedSystemFunctions", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalFunction_RealizingPhysicalFunctions(), thePaPackage.getPhysicalFunction(), thePaPackage.getPhysicalFunction_RealizedLogicalFunctions(), "realizingPhysicalFunctions", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalFunction_ContainedLogicalFunctions(), this.getLogicalFunction(), null, "containedLogicalFunctions", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalFunction_ChildrenLogicalFunctions(), this.getLogicalFunction(), null, "childrenLogicalFunctions", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(logicalFunctionPkgEClass, LogicalFunctionPkg.class, "LogicalFunctionPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLogicalFunctionPkg_OwnedLogicalFunctions(), this.getLogicalFunction(), null, "ownedLogicalFunctions", null, 0, -1, LogicalFunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalFunctionPkg_OwnedLogicalFunctionPkgs(), this.getLogicalFunctionPkg(), null, "ownedLogicalFunctionPkgs", null, 0, -1, LogicalFunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(logicalComponentEClass, LogicalComponent.class, "LogicalComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLogicalComponent_OwnedLogicalComponents(), this.getLogicalComponent(), null, "ownedLogicalComponents", null, 0, -1, LogicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponent_OwnedLogicalArchitectures(), this.getLogicalArchitecture(), null, "ownedLogicalArchitectures", null, 0, -1, LogicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponent_OwnedLogicalComponentPkgs(), this.getLogicalComponentPkg(), null, "ownedLogicalComponentPkgs", null, 0, -1, LogicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponent_SubLogicalComponents(), this.getLogicalComponent(), null, "subLogicalComponents", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponent_AllocatedLogicalFunctions(), this.getLogicalFunction(), this.getLogicalFunction_AllocatingLogicalComponents(), "allocatedLogicalFunctions", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponent_RealizedSystemComponents(), theCtxPackage.getSystemComponent(), null, "realizedSystemComponents", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponent_RealizingPhysicalComponents(), thePaPackage.getPhysicalComponent(), thePaPackage.getPhysicalComponent_RealizedLogicalComponents(), "realizingPhysicalComponents", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(logicalComponentPkgEClass, LogicalComponentPkg.class, "LogicalComponentPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getLogicalComponentPkg_OwnedLogicalComponents(), this.getLogicalComponent(), null, "ownedLogicalComponents", null, 0, -1, LogicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLogicalComponentPkg_OwnedLogicalComponentPkgs(), this.getLogicalComponentPkg(), null, "ownedLogicalComponentPkgs", null, 0, -1, LogicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(capabilityRealizationEClass, CapabilityRealization.class, "CapabilityRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCapabilityRealization_OwnedCapabilityRealizationInvolvements(), theCapellacommonPackage.getCapabilityRealizationInvolvement(), null, "ownedCapabilityRealizationInvolvements", null, 0, -1, CapabilityRealization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityRealization_InvolvedComponents(), theCapellacommonPackage.getCapabilityRealizationInvolvedElement(), null, "involvedComponents", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityRealization_RealizedCapabilities(), theCtxPackage.getCapability(), theCtxPackage.getCapability_RealizingCapabilityRealizations(), "realizedCapabilities", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityRealization_RealizedCapabilityRealizations(), this.getCapabilityRealization(), this.getCapabilityRealization_RealizingCapabilityRealizations(), "realizedCapabilityRealizations", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityRealization_RealizingCapabilityRealizations(), this.getCapabilityRealization(), this.getCapabilityRealization_RealizedCapabilityRealizations(), "realizingCapabilityRealizations", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(capabilityRealizationPkgEClass, CapabilityRealizationPkg.class, "CapabilityRealizationPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCapabilityRealizationPkg_OwnedCapabilityRealizations(), this.getCapabilityRealization(), null, "ownedCapabilityRealizations", null, 0, -1, CapabilityRealizationPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(), this.getCapabilityRealizationPkg(), null, "ownedCapabilityRealizationPkgs", null, 0, -1, CapabilityRealizationPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemAnalysisRealizationEClass, SystemAnalysisRealization.class, "SystemAnalysisRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(contextInterfaceRealizationEClass, ContextInterfaceRealization.class, "ContextInterfaceRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
         "description", "LogicalAnalysis aims at defining the system logical analysis modelling language (close to the OMG Computation Independent Model (CIM)). \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CompositeStructure.ecore\r\nThis package depends on the model Interaction.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitecturePkgEClass,
       source,
       new String[] {
         "description", "Package that contains LogicalArchitecture elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecturePkg_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "description", "Logical architectures set\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitectureEClass,
       source,
       new String[] {
         "description", "Model describing logical architecture part (i.e. Independent from technological choices) - behavioural components & related items - associated to (created during) a modelling phase", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_OwnedLogicalComponentPkg(),
       source,
       new String[] {
         "description", "Link to the package that contains logical components\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_OwnedSystemAnalysisRealizations(),
       source,
       new String[] {
         "description", "Set of system analysis realization links that are owned/contained by the logical architecture\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(),
       source,
       new String[] {
         "description", "(automatically computed) the realisation links from system analysis that point to this logical architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalFunctionEClass,
       source,
       new String[] {
         "description", "Function at Logical level", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_logicalfunction.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_OwnedLogicalFunctionPkgs(),
       source,
       new String[] {
         "description", "Set of subpackages that contain logical function elements", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_AllocatingLogicalComponents(),
       source,
       new String[] {
         "description", "Logical components that allocate this Logical Function", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ChildrenLogicalFunctions(),
       source,
       new String[] {
         "description", "list of children logical functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalFunctionPkgEClass,
       source,
       new String[] {
         "description", "Package that contains logical function elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunctionPkg_OwnedLogicalFunctions(),
       source,
       new String[] {
         "description", "logical function elements contained in this package\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunctionPkg_OwnedLogicalFunctionPkgs(),
       source,
       new String[] {
         "description", "Set of subpackages that contain logical function elements\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentEClass,
       source,
       new String[] {
         "description", "Logical Components are the artifacts enabling decomposition of the system as a \"white box\", independently from any technological solutions. Logical components are identified according to logical abstractions (i.e. functional grouping, logical interfaces)", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "arcadia_description", "Logical Components are the artefacts enabling a notional decomposition of the system as a \"white box\", independently from any technological solutions, but dealing with major system decomposition constraints.", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_logicalcomponent.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponents(),
       source,
       new String[] {
         "description", "children logical components of this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "description", "the various logical architecture (alternatives) associated to this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "description", "logical component packages contained in this logical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_SubLogicalComponents(),
       source,
       new String[] {
         "description", "(automatically computed) the children components of this logical component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_RealizedSystemComponents(),
       source,
       new String[] {
         "description", "System Components that are realized by this Logical Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_RealizingPhysicalComponents(),
       source,
       new String[] {
         "description", "Physical Components that realize this Logical Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentPkgEClass,
       source,
       new String[] {
         "description", "a package containing logical components\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponents(),
       source,
       new String[] {
         "description", "the logical components included in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "description", "sub-packages of this logical component package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationEClass,
       source,
       new String[] {
         "description", "a realization of a capability of the above abstraction level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_capabilityrealizationlogical.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_OwnedCapabilityRealizationInvolvements(),
       source,
       new String[] {
         "description", "the capability realization links that are contained in this CapabilityRealization\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_InvolvedComponents(),
       source,
       new String[] {
         "description", "Components that are involved in this Capability Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationPkgEClass,
       source,
       new String[] {
         "description", "a container for storing CapabilityRealization elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizations(),
       source,
       new String[] {
         "description", "the CapabilityRealizations contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(),
       source,
       new String[] {
         "description", "the sub-packages in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemAnalysisRealizationEClass,
       source,
       new String[] {
         "description", "a realisation link between a system analysis and a logical architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (contextInterfaceRealizationEClass,
       source,
       new String[] {
         "description", "an allocation link between an interface at the logical level, and the system-level interface that it realizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
      (logicalArchitectureEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalArchitecture_OwnedLogicalComponentPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "feature", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_ContainedLogicalFunctionPkg(),
       source,
       new String[] {
         "feature", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalyses(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalArchitecture_AllocatingPhysicalArchitectures(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalFunctionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalFunction_OwnedLogicalFunctionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalFunction_AllocatingLogicalComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_RealizedSystemFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalFunction_RealizingPhysicalFunctions(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ContainedLogicalFunctions(),
       source,
       new String[] {
         "feature", "ownedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ChildrenLogicalFunctions(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalFunctionPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalFunctionPkg_OwnedLogicalFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalFunctionPkg_OwnedLogicalFunctionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (logicalComponentEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_SubLogicalComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_AllocatedLogicalFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_RealizedSystemComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_RealizingPhysicalComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (capabilityRealizationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealization_InvolvedComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealization_RealizedCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealization_RealizedCapabilityRealizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealization_RealizingCapabilityRealizations(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(),
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
      (logicalArchitecturePkgEClass,
       source,
       new String[] {
         "Label", "LogicalArchitecturePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecturePkg_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "Label", "ownedLogicalArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitectureEClass,
       source,
       new String[] {
         "Label", "Logical Architecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_OwnedLogicalComponentPkg(),
       source,
       new String[] {
         "Label", "ownedLogicalComponentPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(),
       source,
       new String[] {
         "Label", "allocatedLogicalArchitectureImplementations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentEClass,
       source,
       new String[] {
         "Label", "LogicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponents(),
       source,
       new String[] {
         "Label", "subLogicalComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "Label", "ownedLogicalArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "Label", "ownedLogicalComponentPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_SubLogicalComponents(),
       source,
       new String[] {
         "Label", "subActors" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentPkgEClass,
       source,
       new String[] {
         "Label", "LogicalComponentPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponents(),
       source,
       new String[] {
         "Label", "ownedLogicalComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "Label", "subLogicalComponentPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationEClass,
       source,
       new String[] {
         "Label", "CapabilityRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationPkgEClass,
       source,
       new String[] {
         "Label", "CapabilityRealizationPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizations(),
       source,
       new String[] {
         "Label", "capabilityRealizations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(),
       source,
       new String[] {
         "Label", "ownedCapabilityRealizationPkgs" //$NON-NLS-1$ //$NON-NLS-2$
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
      (logicalArchitecturePkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LogicalArchitecturePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecturePkg_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitectureEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LogicalArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_OwnedLogicalComponentPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentEClass,
       source,
       new String[] {
         "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LogicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponents(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LogicalComponentPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponents(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.CapabilityRealization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.CapabilityRealizationPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizations(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
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
      (logicalArchitecturePkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecturePkg_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which LogicalArchitecture stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalArchitectureEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_OwnedLogicalComponentPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which LogicalComponentPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_ContainedLogicalFunctionPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_OwnedSystemAnalysisRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ContextArchitectureRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalyses(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatingPhysicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalFunctionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Activity", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "All functions are mapped to (empty) activities", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_OwnedLogicalFunctionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which LogicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_AllocatingLogicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_RealizedSystemFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_RealizingPhysicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ContainedLogicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ChildrenLogicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalFunctionPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunctionPkg_OwnedLogicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which LogicalFunction stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunctionPkg_OwnedLogicalFunctionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which LogicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Blocks::Block", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "cannot map to uml::Component since this mapping is for a SysML profile, and uml::Component is not part of UML4SysML", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Class::nestedClassifier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "the nesting relation is just convenient to store sub-components under a component in the three, even though the hierachical relationship between componenets is not\r\nderived from this nesting : instead, it relies on the Parts present in the component, that are typed by the sub-components types.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Class::nestedClassifier elements on which LogicalComponent stereotype or any stereotype that inherits from it is applied\r\nOrder will not be preserved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Blocks::Block cannot contain LogicalArchitecture\'s equivalent, hence we find the nearest available package to store them.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Blocks::Block cannot contain packages, hence we find the nearest available package to store them.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_SubLogicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_AllocatedLogicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (logicalComponentPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which LogicalComponent stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which LogicalComponentPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::UseCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "needs to be mapped to UseCase since its parent is mapped to UseCase...and has many references mapped to UseCase\'s references", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_RealizedCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_RealizedCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_RealizingCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityRealizationPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which CapabilityRealization stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which CapabilityRealizationPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemAnalysisRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (contextInterfaceRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getLogicalArchitecturePkg_OwnedLogicalArchitectures(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalArchitecture_OwnedLogicalComponentPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalArchitectures(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponent_SubLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getLogicalComponentPkg_OwnedLogicalComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(),
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
      (getLogicalArchitecture_ContainedCapabilityRealizationPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_ContainedLogicalFunctionPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(),
       source,
       new String[] {
         "derive", "self.ownedPartitions.representedElement.oclIsKindOf(PhysicalComponent) -> oclAsType(PhysicalComponent)", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedArchitectureAllocations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatedSystemAnalyses(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalArchitecture_AllocatingPhysicalArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatingArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_AllocatingLogicalComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "LogicalFunction.incomingTraces(self, traces);\r\nComponentFunctionalAllocation.sourceElement(traces, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_RealizedSystemFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outFunctionRealizations.allocatedFunction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_RealizingPhysicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "inFunctionRealizations.allocatingFunction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ContainedLogicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalFunction_ChildrenLogicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "subFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_SubLogicalComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedPartitions.type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_AllocatedLogicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_RealizedSystemComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "realizedComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLogicalComponent_RealizingPhysicalComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatingComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_InvolvedComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CapabilityRealization.ownedCapabilityRealizationInvolvements(self, involvements);\r\nCapabilityRealizationInvolvement.involvedCapabilityRealizationInvolvedElement(involvements, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_RealizedCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CapabilityRealization.outgoingTraces(self, acr);\r\nAbstractCapabilityRealization.realizedCapability(acr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_RealizedCapabilityRealizations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CapabilityRealization.outgoingTraces(self, acr);\r\nAbstractCapabilityRealization.realizedCapability(acr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityRealization_RealizingCapabilityRealizations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CapabilityRealization.incomingTraces(self, acr);\r\nAbstractCapabilityRealization.realizingCapability(acr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //LaPackageImpl
