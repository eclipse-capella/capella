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
package org.polarsys.capella.core.data.ctx.impl;

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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.impl.CsPackageImpl;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
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
public class CtxPackageImpl extends EPackageImpl implements CtxPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemAnalysisEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemFunctionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemFunctionPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemCommunicationHookEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemCommunicationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass capabilityInvolvementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass missionInvolvementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass missionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass missionPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass capabilityEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass capabilityExploitationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass capabilityPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationalAnalysisRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemComponentPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass systemComponentEClass = null;

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
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private CtxPackageImpl() {
    super(eNS_URI, CtxFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link CtxPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static CtxPackage init() {
    if (isInited) return (CtxPackage)EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredCtxPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    CtxPackageImpl theCtxPackage = registeredCtxPackage instanceof CtxPackageImpl ? (CtxPackageImpl)registeredCtxPackage : new CtxPackageImpl();

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
    theCtxPackage.createPackageContents();
    theCapellamodellerPackage.createPackageContents();
    theCapellacorePackage.createPackageContents();
    theOaPackage.createPackageContents();
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
    theCtxPackage.initializePackageContents();
    theCapellamodellerPackage.initializePackageContents();
    theCapellacorePackage.initializePackageContents();
    theOaPackage.initializePackageContents();
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
    theCtxPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(CtxPackage.eNS_URI, theCtxPackage);
    return theCtxPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemAnalysis() {
    return systemAnalysisEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_OwnedSystemComponentPkg() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_OwnedMissionPkg() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_ContainedCapabilityPkg() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_ContainedSystemFunctionPkg() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_OwnedOperationalAnalysisRealizations() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_AllocatedOperationalAnalysisRealizations() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_AllocatedOperationalAnalyses() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemAnalysis_AllocatingLogicalArchitectures() {
    return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemFunction() {
    return systemFunctionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunction_OwnedSystemFunctionPkgs() {
    return (EReference)systemFunctionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunction_AllocatingSystemComponents() {
    return (EReference)systemFunctionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunction_RealizedOperationalActivities() {
    return (EReference)systemFunctionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunction_RealizingLogicalFunctions() {
    return (EReference)systemFunctionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunction_ContainedSystemFunctions() {
    return (EReference)systemFunctionEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunction_ChildrenSystemFunctions() {
    return (EReference)systemFunctionEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemFunctionPkg() {
    return systemFunctionPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunctionPkg_OwnedSystemFunctions() {
    return (EReference)systemFunctionPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemFunctionPkg_OwnedSystemFunctionPkgs() {
    return (EReference)systemFunctionPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemCommunicationHook() {
    return systemCommunicationHookEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemCommunicationHook_Communication() {
    return (EReference)systemCommunicationHookEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemCommunicationHook_Type() {
    return (EReference)systemCommunicationHookEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemCommunication() {
    return systemCommunicationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemCommunication_Ends() {
    return (EReference)systemCommunicationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCapabilityInvolvement() {
    return capabilityInvolvementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityInvolvement_SystemComponent() {
    return (EReference)capabilityInvolvementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityInvolvement_Capability() {
    return (EReference)capabilityInvolvementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMissionInvolvement() {
    return missionInvolvementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMissionInvolvement_SystemComponent() {
    return (EReference)missionInvolvementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMissionInvolvement_Mission() {
    return (EReference)missionInvolvementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMission() {
    return missionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMission_OwnedMissionInvolvements() {
    return (EReference)missionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMission_InvolvedSystemComponents() {
    return (EReference)missionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMission_OwnedCapabilityExploitations() {
    return (EReference)missionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMission_ExploitedCapabilities() {
    return (EReference)missionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMissionPkg() {
    return missionPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMissionPkg_OwnedMissionPkgs() {
    return (EReference)missionPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMissionPkg_OwnedMissions() {
    return (EReference)missionPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCapability() {
    return capabilityEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapability_OwnedCapabilityInvolvements() {
    return (EReference)capabilityEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapability_InvolvedSystemComponents() {
    return (EReference)capabilityEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapability_Purposes() {
    return (EReference)capabilityEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapability_PurposeMissions() {
    return (EReference)capabilityEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapability_RealizedOperationalCapabilities() {
    return (EReference)capabilityEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapability_RealizingCapabilityRealizations() {
    return (EReference)capabilityEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCapabilityExploitation() {
    return capabilityExploitationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityExploitation_Mission() {
    return (EReference)capabilityExploitationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityExploitation_Capability() {
    return (EReference)capabilityExploitationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCapabilityPkg() {
    return capabilityPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityPkg_OwnedCapabilities() {
    return (EReference)capabilityPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCapabilityPkg_OwnedCapabilityPkgs() {
    return (EReference)capabilityPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getOperationalAnalysisRealization() {
    return operationalAnalysisRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemComponentPkg() {
    return systemComponentPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponentPkg_OwnedSystemComponents() {
    return (EReference)systemComponentPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponentPkg_OwnedSystemComponentPkgs() {
    return (EReference)systemComponentPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSystemComponent() {
    return systemComponentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_OwnedSystemComponents() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_OwnedSystemComponentPkgs() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getSystemComponent_DataComponent() {
    return (EAttribute)systemComponentEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_DataType() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_InvolvingCapabilities() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_CapabilityInvolvements() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_InvolvingMissions() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_MissionInvolvements() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_RealizedEntities() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_RealizingLogicalComponents() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSystemComponent_AllocatedSystemFunctions() {
    return (EReference)systemComponentEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CtxFactory getCtxFactory() {
    return (CtxFactory)getEFactoryInstance();
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
    systemAnalysisEClass = createEClass(SYSTEM_ANALYSIS);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_MISSION_PKG);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES);
    createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES);

    systemFunctionEClass = createEClass(SYSTEM_FUNCTION);
    createEReference(systemFunctionEClass, SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS);
    createEReference(systemFunctionEClass, SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS);
    createEReference(systemFunctionEClass, SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES);
    createEReference(systemFunctionEClass, SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS);
    createEReference(systemFunctionEClass, SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS);
    createEReference(systemFunctionEClass, SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS);

    systemFunctionPkgEClass = createEClass(SYSTEM_FUNCTION_PKG);
    createEReference(systemFunctionPkgEClass, SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS);
    createEReference(systemFunctionPkgEClass, SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTION_PKGS);

    systemCommunicationHookEClass = createEClass(SYSTEM_COMMUNICATION_HOOK);
    createEReference(systemCommunicationHookEClass, SYSTEM_COMMUNICATION_HOOK__COMMUNICATION);
    createEReference(systemCommunicationHookEClass, SYSTEM_COMMUNICATION_HOOK__TYPE);

    systemCommunicationEClass = createEClass(SYSTEM_COMMUNICATION);
    createEReference(systemCommunicationEClass, SYSTEM_COMMUNICATION__ENDS);

    capabilityInvolvementEClass = createEClass(CAPABILITY_INVOLVEMENT);
    createEReference(capabilityInvolvementEClass, CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT);
    createEReference(capabilityInvolvementEClass, CAPABILITY_INVOLVEMENT__CAPABILITY);

    missionInvolvementEClass = createEClass(MISSION_INVOLVEMENT);
    createEReference(missionInvolvementEClass, MISSION_INVOLVEMENT__SYSTEM_COMPONENT);
    createEReference(missionInvolvementEClass, MISSION_INVOLVEMENT__MISSION);

    missionEClass = createEClass(MISSION);
    createEReference(missionEClass, MISSION__OWNED_MISSION_INVOLVEMENTS);
    createEReference(missionEClass, MISSION__INVOLVED_SYSTEM_COMPONENTS);
    createEReference(missionEClass, MISSION__OWNED_CAPABILITY_EXPLOITATIONS);
    createEReference(missionEClass, MISSION__EXPLOITED_CAPABILITIES);

    missionPkgEClass = createEClass(MISSION_PKG);
    createEReference(missionPkgEClass, MISSION_PKG__OWNED_MISSION_PKGS);
    createEReference(missionPkgEClass, MISSION_PKG__OWNED_MISSIONS);

    capabilityEClass = createEClass(CAPABILITY);
    createEReference(capabilityEClass, CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
    createEReference(capabilityEClass, CAPABILITY__INVOLVED_SYSTEM_COMPONENTS);
    createEReference(capabilityEClass, CAPABILITY__PURPOSES);
    createEReference(capabilityEClass, CAPABILITY__PURPOSE_MISSIONS);
    createEReference(capabilityEClass, CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES);
    createEReference(capabilityEClass, CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS);

    capabilityExploitationEClass = createEClass(CAPABILITY_EXPLOITATION);
    createEReference(capabilityExploitationEClass, CAPABILITY_EXPLOITATION__MISSION);
    createEReference(capabilityExploitationEClass, CAPABILITY_EXPLOITATION__CAPABILITY);

    capabilityPkgEClass = createEClass(CAPABILITY_PKG);
    createEReference(capabilityPkgEClass, CAPABILITY_PKG__OWNED_CAPABILITIES);
    createEReference(capabilityPkgEClass, CAPABILITY_PKG__OWNED_CAPABILITY_PKGS);

    operationalAnalysisRealizationEClass = createEClass(OPERATIONAL_ANALYSIS_REALIZATION);

    systemComponentPkgEClass = createEClass(SYSTEM_COMPONENT_PKG);
    createEReference(systemComponentPkgEClass, SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS);
    createEReference(systemComponentPkgEClass, SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS);

    systemComponentEClass = createEClass(SYSTEM_COMPONENT);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS);
    createEAttribute(systemComponentEClass, SYSTEM_COMPONENT__DATA_COMPONENT);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__DATA_TYPE);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__INVOLVING_CAPABILITIES);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__INVOLVING_MISSIONS);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__MISSION_INVOLVEMENTS);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__REALIZED_ENTITIES);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS);
    createEReference(systemComponentEClass, SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS);
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
    OaPackage theOaPackage = (OaPackage)EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI);
    LaPackage theLaPackage = (LaPackage)EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    FaPackage theFaPackage = (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    CapellacorePackage theCapellacorePackage = (CapellacorePackage)EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
    InteractionPackage theInteractionPackage = (InteractionPackage)EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    systemAnalysisEClass.getESuperTypes().add(theCsPackage.getComponentArchitecture());
    systemFunctionEClass.getESuperTypes().add(theFaPackage.getAbstractFunction());
    systemFunctionPkgEClass.getESuperTypes().add(theFaPackage.getFunctionPkg());
    systemCommunicationHookEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    systemCommunicationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    capabilityInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
    missionInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
    missionEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    missionEClass.getESuperTypes().add(theCapellacorePackage.getInvolverElement());
    missionPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    capabilityEClass.getESuperTypes().add(theInteractionPackage.getAbstractCapability());
    capabilityExploitationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    capabilityPkgEClass.getESuperTypes().add(theCapellacommonPackage.getAbstractCapabilityPkg());
    operationalAnalysisRealizationEClass.getESuperTypes().add(theCsPackage.getArchitectureAllocation());
    systemComponentPkgEClass.getESuperTypes().add(theCsPackage.getComponentPkg());
    systemComponentEClass.getESuperTypes().add(theCsPackage.getComponent());
    systemComponentEClass.getESuperTypes().add(theCapellacorePackage.getInvolvedElement());

    // Initialize classes and features; add operations and parameters
    initEClass(systemAnalysisEClass, SystemAnalysis.class, "SystemAnalysis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemAnalysis_OwnedSystemComponentPkg(), this.getSystemComponentPkg(), null, "ownedSystemComponentPkg", null, 0, 1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_OwnedMissionPkg(), this.getMissionPkg(), null, "ownedMissionPkg", null, 0, 1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_ContainedCapabilityPkg(), this.getCapabilityPkg(), null, "containedCapabilityPkg", null, 0, 1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_ContainedSystemFunctionPkg(), this.getSystemFunctionPkg(), null, "containedSystemFunctionPkg", null, 0, 1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_OwnedOperationalAnalysisRealizations(), this.getOperationalAnalysisRealization(), null, "ownedOperationalAnalysisRealizations", null, 0, -1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_AllocatedOperationalAnalysisRealizations(), this.getOperationalAnalysisRealization(), null, "allocatedOperationalAnalysisRealizations", null, 0, -1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_AllocatedOperationalAnalyses(), theOaPackage.getOperationalAnalysis(), theOaPackage.getOperationalAnalysis_AllocatingSystemAnalyses(), "allocatedOperationalAnalyses", null, 0, -1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemAnalysis_AllocatingLogicalArchitectures(), theLaPackage.getLogicalArchitecture(), theLaPackage.getLogicalArchitecture_AllocatedSystemAnalyses(), "allocatingLogicalArchitectures", null, 0, -1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemFunctionEClass, SystemFunction.class, "SystemFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemFunction_OwnedSystemFunctionPkgs(), this.getSystemFunctionPkg(), null, "ownedSystemFunctionPkgs", null, 0, -1, SystemFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemFunction_AllocatingSystemComponents(), this.getSystemComponent(), null, "allocatingSystemComponents", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemFunction_RealizedOperationalActivities(), theOaPackage.getOperationalActivity(), theOaPackage.getOperationalActivity_RealizingSystemFunctions(), "realizedOperationalActivities", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemFunction_RealizingLogicalFunctions(), theLaPackage.getLogicalFunction(), theLaPackage.getLogicalFunction_RealizedSystemFunctions(), "realizingLogicalFunctions", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemFunction_ContainedSystemFunctions(), this.getSystemFunction(), null, "containedSystemFunctions", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemFunction_ChildrenSystemFunctions(), this.getSystemFunction(), null, "childrenSystemFunctions", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemFunctionPkgEClass, SystemFunctionPkg.class, "SystemFunctionPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemFunctionPkg_OwnedSystemFunctions(), this.getSystemFunction(), null, "ownedSystemFunctions", null, 0, -1, SystemFunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemFunctionPkg_OwnedSystemFunctionPkgs(), this.getSystemFunctionPkg(), null, "ownedSystemFunctionPkgs", null, 0, -1, SystemFunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemCommunicationHookEClass, SystemCommunicationHook.class, "SystemCommunicationHook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemCommunicationHook_Communication(), this.getSystemCommunication(), null, "communication", null, 0, 1, SystemCommunicationHook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemCommunicationHook_Type(), theCsPackage.getComponent(), null, "type", null, 0, 1, SystemCommunicationHook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemCommunicationEClass, SystemCommunication.class, "SystemCommunication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemCommunication_Ends(), this.getSystemCommunicationHook(), null, "ends", null, 2, 2, SystemCommunication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(capabilityInvolvementEClass, CapabilityInvolvement.class, "CapabilityInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCapabilityInvolvement_SystemComponent(), this.getSystemComponent(), null, "systemComponent", null, 1, 1, CapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityInvolvement_Capability(), this.getCapability(), null, "capability", null, 1, 1, CapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(missionInvolvementEClass, MissionInvolvement.class, "MissionInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getMissionInvolvement_SystemComponent(), this.getSystemComponent(), null, "systemComponent", null, 1, 1, MissionInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMissionInvolvement_Mission(), this.getMission(), null, "mission", null, 1, 1, MissionInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(missionEClass, Mission.class, "Mission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getMission_OwnedMissionInvolvements(), this.getMissionInvolvement(), null, "ownedMissionInvolvements", null, 0, -1, Mission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMission_InvolvedSystemComponents(), this.getSystemComponent(), null, "involvedSystemComponents", null, 0, -1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMission_OwnedCapabilityExploitations(), this.getCapabilityExploitation(), null, "ownedCapabilityExploitations", null, 0, -1, Mission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMission_ExploitedCapabilities(), this.getCapability(), this.getCapability_PurposeMissions(), "exploitedCapabilities", null, 0, -1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(missionPkgEClass, MissionPkg.class, "MissionPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getMissionPkg_OwnedMissionPkgs(), this.getMissionPkg(), null, "ownedMissionPkgs", null, 0, -1, MissionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMissionPkg_OwnedMissions(), this.getMission(), null, "ownedMissions", null, 0, -1, MissionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(capabilityEClass, Capability.class, "Capability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCapability_OwnedCapabilityInvolvements(), this.getCapabilityInvolvement(), null, "ownedCapabilityInvolvements", null, 0, -1, Capability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapability_InvolvedSystemComponents(), this.getSystemComponent(), null, "involvedSystemComponents", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapability_Purposes(), this.getCapabilityExploitation(), null, "purposes", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapability_PurposeMissions(), this.getMission(), this.getMission_ExploitedCapabilities(), "purposeMissions", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapability_RealizedOperationalCapabilities(), theOaPackage.getOperationalCapability(), theOaPackage.getOperationalCapability_RealizingCapabilities(), "realizedOperationalCapabilities", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapability_RealizingCapabilityRealizations(), theLaPackage.getCapabilityRealization(), theLaPackage.getCapabilityRealization_RealizedCapabilities(), "realizingCapabilityRealizations", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(capabilityExploitationEClass, CapabilityExploitation.class, "CapabilityExploitation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCapabilityExploitation_Mission(), this.getMission(), null, "mission", null, 1, 1, CapabilityExploitation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityExploitation_Capability(), this.getCapability(), null, "capability", null, 1, 1, CapabilityExploitation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(capabilityPkgEClass, CapabilityPkg.class, "CapabilityPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCapabilityPkg_OwnedCapabilities(), this.getCapability(), null, "ownedCapabilities", null, 0, -1, CapabilityPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCapabilityPkg_OwnedCapabilityPkgs(), this.getCapabilityPkg(), null, "ownedCapabilityPkgs", null, 0, -1, CapabilityPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(operationalAnalysisRealizationEClass, OperationalAnalysisRealization.class, "OperationalAnalysisRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(systemComponentPkgEClass, SystemComponentPkg.class, "SystemComponentPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemComponentPkg_OwnedSystemComponents(), this.getSystemComponent(), null, "ownedSystemComponents", null, 0, -1, SystemComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponentPkg_OwnedSystemComponentPkgs(), this.getSystemComponentPkg(), null, "ownedSystemComponentPkgs", null, 0, -1, SystemComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(systemComponentEClass, SystemComponent.class, "SystemComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSystemComponent_OwnedSystemComponents(), this.getSystemComponent(), null, "ownedSystemComponents", null, 0, -1, SystemComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_OwnedSystemComponentPkgs(), this.getSystemComponentPkg(), null, "ownedSystemComponentPkgs", null, 0, -1, SystemComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getSystemComponent_DataComponent(), ecorePackage.getEBoolean(), "dataComponent", null, 0, 1, SystemComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_DataType(), theCapellacorePackage.getClassifier(), null, "dataType", null, 0, -1, SystemComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_InvolvingCapabilities(), this.getCapability(), null, "involvingCapabilities", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_CapabilityInvolvements(), this.getCapabilityInvolvement(), null, "capabilityInvolvements", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_InvolvingMissions(), this.getMission(), null, "involvingMissions", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_MissionInvolvements(), this.getMissionInvolvement(), null, "missionInvolvements", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_RealizedEntities(), theOaPackage.getEntity(), null, "realizedEntities", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_RealizingLogicalComponents(), theLaPackage.getLogicalComponent(), null, "realizingLogicalComponents", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSystemComponent_AllocatedSystemFunctions(), this.getSystemFunction(), null, "allocatedSystemFunctions", null, 0, -1, SystemComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

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
         "description", "SystemAnalysis aims at defining the system context analysis modelling language. It is named ContextArchitecture due to MDSysE naming inheritance.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CompositeStructure.ecore\r\nThis package depends on the model Interaction.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemAnalysisEClass,
       source,
       new String[] {
         "description", "Model describing functional and non-functional issues - functions & related items - associated to (created during) a modelling phase", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedSystemComponentPkg(),
       source,
       new String[] {
         "description", "Link to a package that contains System Components", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedMissionPkg(),
       source,
       new String[] {
         "description", "Link to the package that contains system analysis missions\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedOperationalAnalysisRealizations(),
       source,
       new String[] {
         "description", "the realization links between Operational analysis and System analysis that are owned by this System analysis element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatedOperationalAnalysisRealizations(),
       source,
       new String[] {
         "description", "(automatically computed) reference to operational analysis elements that this system analysis is realizing\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemFunctionEClass,
       source,
       new String[] {
         "description", "Function at System level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_systemfunction.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_OwnedSystemFunctionPkgs(),
       source,
       new String[] {
         "description", "sub (function) package under this function", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_AllocatingSystemComponents(),
       source,
       new String[] {
         "description", "Components that allocate this System Function.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ChildrenSystemFunctions(),
       source,
       new String[] {
         "description", "list of children system functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemFunctionPkgEClass,
       source,
       new String[] {
         "description", "a container for System Functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this can be used to structure/organize system functions in the model", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunctionPkg_OwnedSystemFunctions(),
       source,
       new String[] {
         "description", "system functions contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunctionPkg_OwnedSystemFunctionPkgs(),
       source,
       new String[] {
         "description", "sub (function) package under this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationHookEClass,
       source,
       new String[] {
         "description", "an endpoint of a relationship between the System and external actors\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Communication(),
       source,
       new String[] {
         "description", "the relationship link to which this endpoint is attached\r\n[source: Capella study]\r\n\r\nReferences the association of which this property is a member, if any.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Type(),
       source,
       new String[] {
         "description", "the type of the entity to which this endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationEClass,
       source,
       new String[] {
         "description", "a communication relationship between the System (seen as a black box) and some external entities (typically Actors)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunication_Ends(),
       source,
       new String[] {
         "description", "the endpoints of this relationship link (there can be an arbitrary number of them for a given link)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityInvolvementEClass,
       source,
       new String[] {
         "description", "Link between a system component and a system capability that means the system component is involved in the capability\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_actor_capability.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_SystemComponent(),
       source,
       new String[] {
         "description", "Link to a system component that is involved in the system capability.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "description", "Link to the system capability involving the actor\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionInvolvementEClass,
       source,
       new String[] {
         "description", "Link between a system component and a system mission that means the system component is involved in the mission\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_SystemComponent(),
       source,
       new String[] {
         "description", "Link to a system actor that is involved in the system mission\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_Mission(),
       source,
       new String[] {
         "description", "Link to the system mission related to the actor\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionEClass,
       source,
       new String[] {
         "description", "Operational goal. It must be satisfied by usage of System capabilities.\r\n\r\nA mission can be compared to a UML UseCase : A use case is the specification of a set of actions performed by a system, which yields an observable result that is,\r\ntypically, of value for one or more actors or other stakeholders of the system.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_mission.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_OwnedMissionInvolvements(),
       source,
       new String[] {
         "description", "the links between Mission Involvement links that are owned by this Mission", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_InvolvedSystemComponents(),
       source,
       new String[] {
         "description", "System Components that are involved in this Mission", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_OwnedCapabilityExploitations(),
       source,
       new String[] {
         "description", "the capability exploitation links that are assigned to this Mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_ExploitedCapabilities(),
       source,
       new String[] {
         "description", "(automatically computed) the list of Capabilities that this Mission exploits\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionPkgEClass,
       source,
       new String[] {
         "description", "Package that contains system missions\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissionPkgs(),
       source,
       new String[] {
         "description", "Sub packages that contain system missions\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissions(),
       source,
       new String[] {
         "description", "Set of system missions that are defined at that level of package\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityEClass,
       source,
       new String[] {
         "description", "Ability of an organisation, system or process to provide a service that supports the achievement of high-level operational goals", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_actor_capability.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_OwnedCapabilityInvolvements(),
       source,
       new String[] {
         "description", "Capability Involvements owned by this Capability", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_InvolvedSystemComponents(),
       source,
       new String[] {
         "description", "System Components that are involved in this Capability", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_Purposes(),
       source,
       new String[] {
         "description", "the links to the Mission(s) that this Capability supports\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_PurposeMissions(),
       source,
       new String[] {
         "description", "(automatically computed) the Mission(s) that this Capability supports\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityExploitationEClass,
       source,
       new String[] {
         "description", "a relationship between a mission and a capability that it exploits\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Mission(),
       source,
       new String[] {
         "description", "the Mission involved in this relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Capability(),
       source,
       new String[] {
         "description", "the Capability involved in this relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityPkgEClass,
       source,
       new String[] {
         "description", "Package that contains system capabilities\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilities(),
       source,
       new String[] {
         "description", "Set of system capabilities that are defined at that level of package\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilityPkgs(),
       source,
       new String[] {
         "description", "Sub pakages that contain system capabilities\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationalAnalysisRealizationEClass,
       source,
       new String[] {
         "description", "Realization link betwen a system analysis and an operational analysis\r\n[source:Capella study]\r\n\r\nRealization is a specialized abstraction relationship between two sets of model elements, one representing a specification\r\n(the supplier) and the other represents an implementation of the latter (the client). Realization can be used to model\r\nstepwise refinement, optimizations, transformations, templates, model synthesis, framework composition, etc.\r\n[source:UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemComponentPkgEClass,
       source,
       new String[] {
         "description", "a package containing System Components\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponentPkg_OwnedSystemComponents(),
       source,
       new String[] {
         "description", "the System Components included in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponentPkg_OwnedSystemComponentPkgs(),
       source,
       new String[] {
         "description", "sub-packages of this System Component Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemComponentEClass,
       source,
       new String[] {
         "description", "An entity, with discrete structure within the system, that interacts with other Components of the system, thereby contributing at its lowest level to the system properties and characteristics.\r\n[source: Sys EM , ISO/IEC CD 15288]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a (abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_OwnedSystemComponents(),
       source,
       new String[] {
         "description", "the System Components included in this System Component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_OwnedSystemComponentPkgs(),
       source,
       new String[] {
         "description", "sub-packages of this System Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_DataComponent(),
       source,
       new String[] {
         "description", "specifies whether or not this is a data component\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_DataType(),
       source,
       new String[] {
         "description", "data type(s) associated to this component\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_InvolvingCapabilities(),
       source,
       new String[] {
         "description", "Capabilities that involve this System Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_CapabilityInvolvements(),
       source,
       new String[] {
         "description", "The Capability Involvement relationships in which this element is referenced", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_InvolvingMissions(),
       source,
       new String[] {
         "description", "Missions that involve this System Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_MissionInvolvements(),
       source,
       new String[] {
         "description", "The Mission Involvement relationships in which this element is referenced", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_RealizedEntities(),
       source,
       new String[] {
         "description", "Entities that are realized by this System Component", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_RealizingLogicalComponents(),
       source,
       new String[] {
         "description", "Logical Components that realize this System Components", //$NON-NLS-1$ //$NON-NLS-2$
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
      (systemAnalysisEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSystemAnalysis_OwnedSystemComponentPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemAnalysis_OwnedMissionPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemAnalysis_ContainedCapabilityPkg(),
       source,
       new String[] {
         "feature", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_ContainedSystemFunctionPkg(),
       source,
       new String[] {
         "feature", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatedOperationalAnalyses(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemAnalysis_AllocatingLogicalArchitectures(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemFunctionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSystemFunction_OwnedSystemFunctionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemFunction_AllocatingSystemComponents(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_RealizedOperationalActivities(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemFunction_RealizingLogicalFunctions(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ContainedSystemFunctions(),
       source,
       new String[] {
         "feature", "ownedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ChildrenSystemFunctions(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemFunctionPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSystemFunctionPkg_OwnedSystemFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemFunctionPkg_OwnedSystemFunctionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (missionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getMission_InvolvedSystemComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getMission_ExploitedCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (missionPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getMissionPkg_OwnedMissionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getMissionPkg_OwnedMissions(),
       source,
       new String[] {
       });
    addAnnotation
      (capabilityEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCapability_InvolvedSystemComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapability_PurposeMissions(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_RealizedOperationalCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapability_RealizingCapabilityRealizations(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilityPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (systemComponentPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponentPkg_OwnedSystemComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponentPkg_OwnedSystemComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (systemComponentEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_OwnedSystemComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_OwnedSystemComponentPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_DataComponent(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_InvolvingCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_CapabilityInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_InvolvingMissions(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_MissionInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_RealizedEntities(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_RealizingLogicalComponents(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_AllocatedSystemFunctions(),
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
      (systemAnalysisEClass,
       source,
       new String[] {
         "Label", "System Analysis" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedMissionPkg(),
       source,
       new String[] {
         "Label", "ownedMissionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationHookEClass,
       source,
       new String[] {
         "Label", "Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Communication(),
       source,
       new String[] {
         "Label", "communication" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Type(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationEClass,
       source,
       new String[] {
         "Label", "SystemCommunication" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunication_Ends(),
       source,
       new String[] {
         "Label", "system" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityInvolvementEClass,
       source,
       new String[] {
         "Label", "CapabilityInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "Label", "capability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionInvolvementEClass,
       source,
       new String[] {
         "Label", "MissionInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_SystemComponent(),
       source,
       new String[] {
         "Label", "actor" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_Mission(),
       source,
       new String[] {
         "Label", "mission" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionEClass,
       source,
       new String[] {
         "Label", "Mission" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_OwnedCapabilityExploitations(),
       source,
       new String[] {
         "Label", "capabilityExploitations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_ExploitedCapabilities(),
       source,
       new String[] {
         "Label", "exploitedCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionPkgEClass,
       source,
       new String[] {
         "Label", "MissionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissionPkgs(),
       source,
       new String[] {
         "Label", "subMissionPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissions(),
       source,
       new String[] {
         "Label", "ownedMissions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityEClass,
       source,
       new String[] {
         "Label", "Capability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_Purposes(),
       source,
       new String[] {
         "Label", "purposes" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_PurposeMissions(),
       source,
       new String[] {
         "Label", "purposeMissions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityExploitationEClass,
       source,
       new String[] {
         "Label", "CapabilityExploitation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Mission(),
       source,
       new String[] {
         "Label", "mission" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Capability(),
       source,
       new String[] {
         "Label", "capability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityPkgEClass,
       source,
       new String[] {
         "Label", "CapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilities(),
       source,
       new String[] {
         "Label", "capabilities" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilityPkgs(),
       source,
       new String[] {
         "Label", "subCapabilityPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemComponentEClass,
       source,
       new String[] {
         "Label", "SystemComponent" //$NON-NLS-1$ //$NON-NLS-2$
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
      (systemAnalysisEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.ContextArchitecture" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedMissionPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationHookEClass,
       source,
       new String[] {
         "metaclass", "Property", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.SystemCommunicationHook" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Communication(),
       source,
       new String[] {
         "featureName", "association", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Type(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationEClass,
       source,
       new String[] {
         "metaclass", "Association", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.SystemCommunication" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunication_Ends(),
       source,
       new String[] {
         "featureName", "navigableOwnedEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Association" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityInvolvementEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.CapabilityInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionInvolvementEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.MissionInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_SystemComponent(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_Mission(),
       source,
       new String[] {
         "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionEClass,
       source,
       new String[] {
         "metaclass", "UseCase", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Mission" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_OwnedCapabilityExploitations(),
       source,
       new String[] {
         "featureName", "include", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "UseCase" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.MissionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissionPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissions(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Capability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_Purposes(),
       source,
       new String[] {
         "umlOppositeReference", "addition", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Include" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityExploitationEClass,
       source,
       new String[] {
         "metaclass", "Include", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.CapabilityExploitation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Mission(),
       source,
       new String[] {
         "featureName", "includingCase", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Include" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Capability(),
       source,
       new String[] {
         "featureName", "addition", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Include" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.CapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilities(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilityPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemComponentEClass,
       source,
       new String[] {
         "metaclass", "Component" //$NON-NLS-1$ //$NON-NLS-2$
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
      (systemAnalysisEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedMissionPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which MissionPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_ContainedCapabilityPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_ContainedSystemFunctionPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_OwnedOperationalAnalysisRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which OperationalAnalysisRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatedOperationalAnalysisRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatedOperationalAnalyses(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatingLogicalArchitectures(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemFunctionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Activity", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "All functions are mapped to Activities. Parent activities refer to children activities via CallBehaviorActions", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_OwnedSystemFunctionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which SystemFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_RealizedOperationalActivities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_RealizingLogicalFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ContainedSystemFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ChildrenSystemFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemFunctionPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunctionPkg_OwnedSystemFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunctionPkg_OwnedSystemFunctionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which SystemFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationHookEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Property", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Communication(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Property::association", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunicationHook_Type(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::TypedElement::type", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemCommunicationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Association", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemCommunication_Ends(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Association::ownedEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityInvolvementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionInvolvementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_SystemComponent(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_Mission(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::UseCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_OwnedCapabilityExploitations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::UseCase::include", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_ExploitedCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (missionPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissionPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which MissionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionPkg_OwnedMissions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Mission stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::UseCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_Purposes(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Include::addition", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which CapabilityExploitation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_PurposeMissions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_RealizedOperationalCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_RealizingCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityExploitationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Include", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Mission(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Include::includingCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Capability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Include::addition", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (capabilityPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilityPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationalAnalysisRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponentPkg_OwnedSystemComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponentPkg_OwnedSystemComponentPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (systemComponentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_OwnedSystemComponents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_OwnedSystemComponentPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_DataComponent(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_DataType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_AllocatedSystemFunctions(),
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
      (getSystemAnalysis_OwnedMissionPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemCommunicationHook_Communication(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemCommunicationHook_Type(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemCommunication_Ends(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityInvolvement_Capability(),
       source,
       new String[] {
       });
    addAnnotation
      (getMissionInvolvement_SystemComponent(),
       source,
       new String[] {
       });
    addAnnotation
      (getMissionInvolvement_Mission(),
       source,
       new String[] {
       });
    addAnnotation
      (getMission_OwnedCapabilityExploitations(),
       source,
       new String[] {
       });
    addAnnotation
      (getMission_ExploitedCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getMissionPkg_OwnedMissionPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getMissionPkg_OwnedMissions(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapability_Purposes(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapability_PurposeMissions(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityExploitation_Mission(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityExploitation_Capability(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getCapabilityPkg_OwnedCapabilityPkgs(),
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
      (getSystemAnalysis_ContainedCapabilityPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedAbstractCapabilityPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_ContainedSystemFunctionPkg(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatedOperationalAnalysisRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "provisionedArchitectureAllocations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatedOperationalAnalyses(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemAnalysis_AllocatingLogicalArchitectures(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatingArchitectures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_AllocatingSystemComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "SystemFunction.incomingTraces(self, traces);\r\nComponentFunctionalAllocation.sourceElement(traces, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_RealizedOperationalActivities(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outFunctionRealizations.allocatedFunction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_RealizingLogicalFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "inFunctionRealizations.allocatingFunction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ContainedSystemFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemFunction_ChildrenSystemFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "subFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_SystemComponent(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involver" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_SystemComponent(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMissionInvolvement_Mission(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involver" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_InvolvedSystemComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Mission.ownedMissionInvolvements(self, missionInvolvements);\r\nMissionInvolvement.systemComponent(missionInvolvements, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMission_ExploitedCapabilities(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedCapabilityExploitations.capability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_InvolvedSystemComponents(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Capability.ownedCapabilityInvolvements(self, involvements);\r\nCapabilityInvolvement.systemComponent(involvements, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_Purposes(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "capability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_PurposeMissions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "purposes.mission" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_RealizedOperationalCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractCapabilityRealization.sourceElement(acr, self);\r\nAbstractCapabilityRealization.realizedCapability(acr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapability_RealizingCapabilityRealizations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractCapabilityRealization.targetElement(acr, self);\r\nAbstractCapabilityRealization.realizingCapability(acr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCapabilityExploitation_Mission(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedCapabilityExploitations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_InvolvingCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "SystemComponent.capabilityInvolvements(self, capabilityInvolvements);\r\nCapabilityInvolvement.involver(capabilityInvolvements, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_CapabilityInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getSystemComponent_InvolvingMissions(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "SystemComponent.missionInvolvements(self, missionInvolvements);\r\nMissionInvolvement.involver(missionInvolvements, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_MissionInvolvements(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involvingInvolvements" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_RealizedEntities(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "realizingComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_RealizingLogicalComponents(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "realizingComponents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSystemComponent_AllocatedSystemFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "allocatedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
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
      (capabilityInvolvementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (missionInvolvementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (capabilityExploitationEClass,
       source,
       new String[] {
       });
  }

} //CtxPackageImpl
