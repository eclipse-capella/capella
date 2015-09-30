/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.impl.CsPackageImpl;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
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
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.impl.RequirementPackageImpl;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl;

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
	private EClass systemEClass = null;

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
	private EClass actorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actorCapabilityInvolvementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actorMissionInvolvementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actorPkgEClass = null;

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
	private EClass systemMissionInvolvementEClass = null;

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
	private EClass systemCapabilityInvolvementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationalActorRealizationEClass = null;

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
	private EClass operationalEntityRealizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemContextEClass = null;

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
		CtxPackageImpl theCtxPackage = (CtxPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CtxPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CtxPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ActivityPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CapellamodellerPackageImpl theCapellamodellerPackage = (CapellamodellerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI) instanceof CapellamodellerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI) : CapellamodellerPackage.eINSTANCE);
		CapellacorePackageImpl theCapellacorePackage = (CapellacorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI) instanceof CapellacorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI) : CapellacorePackage.eINSTANCE);
		OaPackageImpl theOaPackage = (OaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI) instanceof OaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI) : OaPackage.eINSTANCE);
		LaPackageImpl theLaPackage = (LaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI) instanceof LaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI) : LaPackage.eINSTANCE);
		PaPackageImpl thePaPackage = (PaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI) instanceof PaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI) : PaPackage.eINSTANCE);
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) : DeploymentPackage.eINSTANCE);
		EpbsPackageImpl theEpbsPackage = (EpbsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI) instanceof EpbsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI) : EpbsPackage.eINSTANCE);
		SharedmodelPackageImpl theSharedmodelPackage = (SharedmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI) instanceof SharedmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI) : SharedmodelPackage.eINSTANCE);
		RequirementPackageImpl theRequirementPackage = (RequirementPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RequirementPackage.eNS_URI) instanceof RequirementPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RequirementPackage.eNS_URI) : RequirementPackage.eINSTANCE);
		CapellacommonPackageImpl theCapellacommonPackage = (CapellacommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI) instanceof CapellacommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI) : CapellacommonPackage.eINSTANCE);
		InformationPackageImpl theInformationPackage = (InformationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI) instanceof InformationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI) : InformationPackage.eINSTANCE);
		CommunicationPackageImpl theCommunicationPackage = (CommunicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI) instanceof CommunicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI) : CommunicationPackage.eINSTANCE);
		DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) instanceof DatatypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) : DatatypePackage.eINSTANCE);
		DatavaluePackageImpl theDatavaluePackage = (DatavaluePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI) instanceof DatavaluePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI) : DatavaluePackage.eINSTANCE);
		CsPackageImpl theCsPackage = (CsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI) instanceof CsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI) : CsPackage.eINSTANCE);
		FaPackageImpl theFaPackage = (FaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI) instanceof FaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI) : FaPackage.eINSTANCE);
		InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI) instanceof InteractionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI) : InteractionPackage.eINSTANCE);

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
		theRequirementPackage.createPackageContents();
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
		theRequirementPackage.initializePackageContents();
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
	public EClass getSystemAnalysis() {
		return systemAnalysisEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_OwnedSystemContext() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_OwnedSystem() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_OwnedActorPkg() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_OwnedMissionPkg() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_ContainedCapabilityPkg() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_ContainedSystemFunctionPkg() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_OwnedOperationalAnalysisRealizations() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_AllocatedOperationalAnalysisRealizations() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_AllocatedOperationalAnalyses() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemAnalysis_AllocatingLogicalArchitectures() {
		return (EReference)systemAnalysisEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystem() {
		return systemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_ContributedCapabilities() {
		return (EReference)systemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_ParticipationsInCapabilities() {
		return (EReference)systemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_ContributedMissions() {
		return (EReference)systemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_ParticipationsInMissions() {
		return (EReference)systemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_ExternalCommunication() {
		return (EReference)systemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_OwnedEntityRealizations() {
		return (EReference)systemEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_AllocatedEntityRealizations() {
		return (EReference)systemEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_AllocatedSystemFunctions() {
		return (EReference)systemEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_RealizedEntities() {
		return (EReference)systemEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystem_RealizingLogicalComponents() {
		return (EReference)systemEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemFunction() {
		return systemFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_OwnedSystemFunctionPkgs() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_AllocatorActors() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_AllocatorSystems() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_RealizedOperationalActivities() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_RealizingLogicalFunctions() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_ContainedSystemFunctions() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunction_ChildrenSystemFunctions() {
		return (EReference)systemFunctionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemFunctionPkg() {
		return systemFunctionPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunctionPkg_OwnedSystemFunctions() {
		return (EReference)systemFunctionPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemFunctionPkg_OwnedSystemFunctionPkgs() {
		return (EReference)systemFunctionPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemCommunicationHook() {
		return systemCommunicationHookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemCommunicationHook_Communication() {
		return (EReference)systemCommunicationHookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemCommunicationHook_Type() {
		return (EReference)systemCommunicationHookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemCommunication() {
		return systemCommunicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemCommunication_Ends() {
		return (EReference)systemCommunicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActor() {
		return actorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_ParticipationsInMissions() {
		return (EReference)actorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_ParticipationsInCapabilities() {
		return (EReference)actorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_ParticipationsInCapabilityRealizations() {
		return (EReference)actorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_ContributedMissions() {
		return (EReference)actorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_ContributedCapabilities() {
		return (EReference)actorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_SystemCommunication() {
		return (EReference)actorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_OwnedOperationalActorRealizations() {
		return (EReference)actorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_OwnedOperationalEntityRealizations() {
		return (EReference)actorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_AllocatedSystemFunctions() {
		return (EReference)actorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_RealizedEntities() {
		return (EReference)actorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_RealizedOperationalActors() {
		return (EReference)actorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_RealizingLogicalActors() {
		return (EReference)actorEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActorCapabilityInvolvement() {
		return actorCapabilityInvolvementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorCapabilityInvolvement_Actor() {
		return (EReference)actorCapabilityInvolvementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorCapabilityInvolvement_Capability() {
		return (EReference)actorCapabilityInvolvementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActorMissionInvolvement() {
		return actorMissionInvolvementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorMissionInvolvement_Actor() {
		return (EReference)actorMissionInvolvementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorMissionInvolvement_Mission() {
		return (EReference)actorMissionInvolvementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActorPkg() {
		return actorPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorPkg_OwnedActors() {
		return (EReference)actorPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorPkg_OwnedActorPkgs() {
		return (EReference)actorPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActorPkg_OwnedSystemCommunication() {
		return (EReference)actorPkgEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMission() {
		return missionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_OwnedActorMissionInvolvements() {
		return (EReference)missionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_OwnedSystemMissionInvolvement() {
		return (EReference)missionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_OwnedCapabilityExploitations() {
		return (EReference)missionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_ParticipatingActors() {
		return (EReference)missionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_ParticipatingSystem() {
		return (EReference)missionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_InvolvedActors() {
		return (EReference)missionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_InvolvedSystem() {
		return (EReference)missionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMission_ExploitedCapabilities() {
		return (EReference)missionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMissionPkg() {
		return missionPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMissionPkg_OwnedMissionPkgs() {
		return (EReference)missionPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMissionPkg_OwnedMissions() {
		return (EReference)missionPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemMissionInvolvement() {
		return systemMissionInvolvementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemMissionInvolvement_Mission() {
		return (EReference)systemMissionInvolvementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemMissionInvolvement_System() {
		return (EReference)systemMissionInvolvementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapability() {
		return capabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_OwnedActorCapabilityInvolvements() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_OwnedSystemCapabilityInvolvement() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_InvolvedActors() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_InvolvedSystem() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_ParticipatingActors() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_ParticipatingSystem() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_Purposes() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_PurposeMissions() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_RealizedOperationalCapabilities() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapability_RealizingCapabilityRealizations() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapabilityExploitation() {
		return capabilityExploitationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityExploitation_Mission() {
		return (EReference)capabilityExploitationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityExploitation_Capability() {
		return (EReference)capabilityExploitationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapabilityPkg() {
		return capabilityPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityPkg_OwnedCapabilities() {
		return (EReference)capabilityPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityPkg_OwnedCapabilityPkgs() {
		return (EReference)capabilityPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemCapabilityInvolvement() {
		return systemCapabilityInvolvementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemCapabilityInvolvement_Capability() {
		return (EReference)systemCapabilityInvolvementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemCapabilityInvolvement_System() {
		return (EReference)systemCapabilityInvolvementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationalActorRealization() {
		return operationalActorRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationalAnalysisRealization() {
		return operationalAnalysisRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationalEntityRealization() {
		return operationalEntityRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemContext() {
		return systemContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_SYSTEM);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_ACTOR_PKG);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_MISSION_PKG);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES);
		createEReference(systemAnalysisEClass, SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES);

		systemEClass = createEClass(SYSTEM);
		createEReference(systemEClass, SYSTEM__CONTRIBUTED_CAPABILITIES);
		createEReference(systemEClass, SYSTEM__PARTICIPATIONS_IN_CAPABILITIES);
		createEReference(systemEClass, SYSTEM__CONTRIBUTED_MISSIONS);
		createEReference(systemEClass, SYSTEM__PARTICIPATIONS_IN_MISSIONS);
		createEReference(systemEClass, SYSTEM__EXTERNAL_COMMUNICATION);
		createEReference(systemEClass, SYSTEM__OWNED_ENTITY_REALIZATIONS);
		createEReference(systemEClass, SYSTEM__ALLOCATED_ENTITY_REALIZATIONS);
		createEReference(systemEClass, SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS);
		createEReference(systemEClass, SYSTEM__REALIZED_ENTITIES);
		createEReference(systemEClass, SYSTEM__REALIZING_LOGICAL_COMPONENTS);

		systemFunctionEClass = createEClass(SYSTEM_FUNCTION);
		createEReference(systemFunctionEClass, SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS);
		createEReference(systemFunctionEClass, SYSTEM_FUNCTION__ALLOCATOR_ACTORS);
		createEReference(systemFunctionEClass, SYSTEM_FUNCTION__ALLOCATOR_SYSTEMS);
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

		actorEClass = createEClass(ACTOR);
		createEReference(actorEClass, ACTOR__PARTICIPATIONS_IN_MISSIONS);
		createEReference(actorEClass, ACTOR__PARTICIPATIONS_IN_CAPABILITIES);
		createEReference(actorEClass, ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS);
		createEReference(actorEClass, ACTOR__CONTRIBUTED_MISSIONS);
		createEReference(actorEClass, ACTOR__CONTRIBUTED_CAPABILITIES);
		createEReference(actorEClass, ACTOR__SYSTEM_COMMUNICATION);
		createEReference(actorEClass, ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS);
		createEReference(actorEClass, ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS);
		createEReference(actorEClass, ACTOR__ALLOCATED_SYSTEM_FUNCTIONS);
		createEReference(actorEClass, ACTOR__REALIZED_ENTITIES);
		createEReference(actorEClass, ACTOR__REALIZED_OPERATIONAL_ACTORS);
		createEReference(actorEClass, ACTOR__REALIZING_LOGICAL_ACTORS);

		actorCapabilityInvolvementEClass = createEClass(ACTOR_CAPABILITY_INVOLVEMENT);
		createEReference(actorCapabilityInvolvementEClass, ACTOR_CAPABILITY_INVOLVEMENT__ACTOR);
		createEReference(actorCapabilityInvolvementEClass, ACTOR_CAPABILITY_INVOLVEMENT__CAPABILITY);

		actorMissionInvolvementEClass = createEClass(ACTOR_MISSION_INVOLVEMENT);
		createEReference(actorMissionInvolvementEClass, ACTOR_MISSION_INVOLVEMENT__ACTOR);
		createEReference(actorMissionInvolvementEClass, ACTOR_MISSION_INVOLVEMENT__MISSION);

		actorPkgEClass = createEClass(ACTOR_PKG);
		createEReference(actorPkgEClass, ACTOR_PKG__OWNED_ACTORS);
		createEReference(actorPkgEClass, ACTOR_PKG__OWNED_ACTOR_PKGS);
		createEReference(actorPkgEClass, ACTOR_PKG__OWNED_SYSTEM_COMMUNICATION);

		missionEClass = createEClass(MISSION);
		createEReference(missionEClass, MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS);
		createEReference(missionEClass, MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT);
		createEReference(missionEClass, MISSION__OWNED_CAPABILITY_EXPLOITATIONS);
		createEReference(missionEClass, MISSION__PARTICIPATING_ACTORS);
		createEReference(missionEClass, MISSION__PARTICIPATING_SYSTEM);
		createEReference(missionEClass, MISSION__INVOLVED_ACTORS);
		createEReference(missionEClass, MISSION__INVOLVED_SYSTEM);
		createEReference(missionEClass, MISSION__EXPLOITED_CAPABILITIES);

		missionPkgEClass = createEClass(MISSION_PKG);
		createEReference(missionPkgEClass, MISSION_PKG__OWNED_MISSION_PKGS);
		createEReference(missionPkgEClass, MISSION_PKG__OWNED_MISSIONS);

		systemMissionInvolvementEClass = createEClass(SYSTEM_MISSION_INVOLVEMENT);
		createEReference(systemMissionInvolvementEClass, SYSTEM_MISSION_INVOLVEMENT__MISSION);
		createEReference(systemMissionInvolvementEClass, SYSTEM_MISSION_INVOLVEMENT__SYSTEM);

		capabilityEClass = createEClass(CAPABILITY);
		createEReference(capabilityEClass, CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS);
		createEReference(capabilityEClass, CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT);
		createEReference(capabilityEClass, CAPABILITY__INVOLVED_ACTORS);
		createEReference(capabilityEClass, CAPABILITY__INVOLVED_SYSTEM);
		createEReference(capabilityEClass, CAPABILITY__PARTICIPATING_ACTORS);
		createEReference(capabilityEClass, CAPABILITY__PARTICIPATING_SYSTEM);
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

		systemCapabilityInvolvementEClass = createEClass(SYSTEM_CAPABILITY_INVOLVEMENT);
		createEReference(systemCapabilityInvolvementEClass, SYSTEM_CAPABILITY_INVOLVEMENT__CAPABILITY);
		createEReference(systemCapabilityInvolvementEClass, SYSTEM_CAPABILITY_INVOLVEMENT__SYSTEM);

		operationalActorRealizationEClass = createEClass(OPERATIONAL_ACTOR_REALIZATION);

		operationalAnalysisRealizationEClass = createEClass(OPERATIONAL_ANALYSIS_REALIZATION);

		operationalEntityRealizationEClass = createEClass(OPERATIONAL_ENTITY_REALIZATION);

		systemContextEClass = createEClass(SYSTEM_CONTEXT);
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
		CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
		FaPackage theFaPackage = (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
		CapellacorePackage theCapellacorePackage = (CapellacorePackage)EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
		InteractionPackage theInteractionPackage = (InteractionPackage)EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		systemAnalysisEClass.getESuperTypes().add(theCsPackage.getComponentArchitecture());
		systemEClass.getESuperTypes().add(theCsPackage.getComponent());
		systemEClass.getESuperTypes().add(theCapellacommonPackage.getCapabilityRealizationInvolvedElement());
		systemFunctionEClass.getESuperTypes().add(theFaPackage.getAbstractFunction());
		systemFunctionPkgEClass.getESuperTypes().add(theFaPackage.getFunctionPkg());
		systemCommunicationHookEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
		systemCommunicationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
		actorEClass.getESuperTypes().add(theCsPackage.getAbstractActor());
		actorCapabilityInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
		actorMissionInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
		actorPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
		missionEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
		missionEClass.getESuperTypes().add(theCapellacorePackage.getInvolverElement());
		missionPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
		systemMissionInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
		capabilityEClass.getESuperTypes().add(theInteractionPackage.getAbstractCapability());
		capabilityExploitationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
		capabilityPkgEClass.getESuperTypes().add(theCapellacommonPackage.getAbstractCapabilityPkg());
		systemCapabilityInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
		operationalActorRealizationEClass.getESuperTypes().add(theCsPackage.getComponentAllocation());
		operationalAnalysisRealizationEClass.getESuperTypes().add(theCsPackage.getArchitectureAllocation());
		operationalEntityRealizationEClass.getESuperTypes().add(theCsPackage.getComponentAllocation());
		systemContextEClass.getESuperTypes().add(theCsPackage.getComponentContext());

		// Initialize classes and features; add operations and parameters
		initEClass(systemAnalysisEClass, SystemAnalysis.class, "SystemAnalysis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSystemAnalysis_OwnedSystemContext(), this.getSystemContext(), null, "ownedSystemContext", null, 0, 1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_OwnedSystem(), this.getSystem(), null, "ownedSystem", null, 1, 1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_OwnedActorPkg(), this.getActorPkg(), null, "ownedActorPkg", null, 0, 1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_OwnedMissionPkg(), this.getMissionPkg(), null, "ownedMissionPkg", null, 0, 1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_ContainedCapabilityPkg(), this.getCapabilityPkg(), null, "containedCapabilityPkg", null, 0, 1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_ContainedSystemFunctionPkg(), this.getSystemFunctionPkg(), null, "containedSystemFunctionPkg", null, 0, 1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_OwnedOperationalAnalysisRealizations(), this.getOperationalAnalysisRealization(), null, "ownedOperationalAnalysisRealizations", null, 0, -1, SystemAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_AllocatedOperationalAnalysisRealizations(), this.getOperationalAnalysisRealization(), null, "allocatedOperationalAnalysisRealizations", null, 0, -1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_AllocatedOperationalAnalyses(), theOaPackage.getOperationalAnalysis(), theOaPackage.getOperationalAnalysis_AllocatingSystemAnalyses(), "allocatedOperationalAnalyses", null, 0, -1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemAnalysis_AllocatingLogicalArchitectures(), theLaPackage.getLogicalArchitecture(), theLaPackage.getLogicalArchitecture_AllocatedSystemAnalyses(), "allocatingLogicalArchitectures", null, 0, -1, SystemAnalysis.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemEClass, org.polarsys.capella.core.data.ctx.System.class, "System", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSystem_ContributedCapabilities(), this.getCapability(), this.getCapability_ParticipatingSystem(), "contributedCapabilities", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_ParticipationsInCapabilities(), this.getSystemCapabilityInvolvement(), this.getSystemCapabilityInvolvement_System(), "participationsInCapabilities", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_ContributedMissions(), this.getMission(), this.getMission_ParticipatingSystem(), "contributedMissions", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_ParticipationsInMissions(), this.getSystemMissionInvolvement(), this.getSystemMissionInvolvement_System(), "participationsInMissions", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_ExternalCommunication(), this.getSystemCommunicationHook(), null, "externalCommunication", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_OwnedEntityRealizations(), this.getOperationalEntityRealization(), null, "ownedEntityRealizations", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_AllocatedEntityRealizations(), this.getOperationalEntityRealization(), null, "allocatedEntityRealizations", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_AllocatedSystemFunctions(), this.getSystemFunction(), this.getSystemFunction_AllocatorSystems(), "allocatedSystemFunctions", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_RealizedEntities(), theOaPackage.getEntity(), null, "realizedEntities", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystem_RealizingLogicalComponents(), theLaPackage.getLogicalComponent(), theLaPackage.getLogicalComponent_RealizedSystems(), "realizingLogicalComponents", null, 0, -1, org.polarsys.capella.core.data.ctx.System.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemFunctionEClass, SystemFunction.class, "SystemFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSystemFunction_OwnedSystemFunctionPkgs(), this.getSystemFunctionPkg(), null, "ownedSystemFunctionPkgs", null, 0, -1, SystemFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemFunction_AllocatorActors(), this.getActor(), this.getActor_AllocatedSystemFunctions(), "allocatorActors", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemFunction_AllocatorSystems(), this.getSystem(), this.getSystem_AllocatedSystemFunctions(), "allocatorSystems", null, 0, -1, SystemFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
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

		initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getActor_ParticipationsInMissions(), this.getActorMissionInvolvement(), this.getActorMissionInvolvement_Actor(), "participationsInMissions", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_ParticipationsInCapabilities(), this.getActorCapabilityInvolvement(), this.getActorCapabilityInvolvement_Actor(), "participationsInCapabilities", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_ParticipationsInCapabilityRealizations(), theCsPackage.getActorCapabilityRealizationInvolvement(), null, "participationsInCapabilityRealizations", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_ContributedMissions(), this.getMission(), this.getMission_ParticipatingActors(), "contributedMissions", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_ContributedCapabilities(), this.getCapability(), this.getCapability_ParticipatingActors(), "contributedCapabilities", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_SystemCommunication(), this.getSystemCommunicationHook(), null, "systemCommunication", null, 0, 1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_OwnedOperationalActorRealizations(), this.getOperationalActorRealization(), null, "ownedOperationalActorRealizations", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_OwnedOperationalEntityRealizations(), this.getOperationalEntityRealization(), null, "ownedOperationalEntityRealizations", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_AllocatedSystemFunctions(), this.getSystemFunction(), this.getSystemFunction_AllocatorActors(), "allocatedSystemFunctions", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_RealizedEntities(), theOaPackage.getEntity(), theOaPackage.getEntity_RealizingActors(), "realizedEntities", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_RealizedOperationalActors(), theOaPackage.getOperationalActor(), theOaPackage.getOperationalActor_RealizingSystemActors(), "realizedOperationalActors", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActor_RealizingLogicalActors(), theLaPackage.getLogicalActor(), theLaPackage.getLogicalActor_RealizedSystemActors(), "realizingLogicalActors", null, 0, -1, Actor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(actorCapabilityInvolvementEClass, ActorCapabilityInvolvement.class, "ActorCapabilityInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getActorCapabilityInvolvement_Actor(), this.getActor(), this.getActor_ParticipationsInCapabilities(), "actor", null, 1, 1, ActorCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActorCapabilityInvolvement_Capability(), this.getCapability(), this.getCapability_InvolvedActors(), "capability", null, 1, 1, ActorCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(actorMissionInvolvementEClass, ActorMissionInvolvement.class, "ActorMissionInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getActorMissionInvolvement_Actor(), this.getActor(), this.getActor_ParticipationsInMissions(), "actor", null, 1, 1, ActorMissionInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActorMissionInvolvement_Mission(), this.getMission(), this.getMission_InvolvedActors(), "mission", null, 1, 1, ActorMissionInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(actorPkgEClass, ActorPkg.class, "ActorPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getActorPkg_OwnedActors(), this.getActor(), null, "ownedActors", null, 0, -1, ActorPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActorPkg_OwnedActorPkgs(), this.getActorPkg(), null, "ownedActorPkgs", null, 0, -1, ActorPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActorPkg_OwnedSystemCommunication(), this.getSystemCommunication(), null, "ownedSystemCommunication", null, 0, -1, ActorPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(missionEClass, Mission.class, "Mission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMission_OwnedActorMissionInvolvements(), this.getActorMissionInvolvement(), null, "ownedActorMissionInvolvements", null, 0, -1, Mission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_OwnedSystemMissionInvolvement(), this.getSystemMissionInvolvement(), null, "ownedSystemMissionInvolvement", null, 0, 1, Mission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_OwnedCapabilityExploitations(), this.getCapabilityExploitation(), this.getCapabilityExploitation_Mission(), "ownedCapabilityExploitations", null, 0, -1, Mission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_ParticipatingActors(), this.getActor(), this.getActor_ContributedMissions(), "participatingActors", null, 0, -1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_ParticipatingSystem(), this.getSystem(), this.getSystem_ContributedMissions(), "participatingSystem", null, 0, 1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_InvolvedActors(), this.getActorMissionInvolvement(), this.getActorMissionInvolvement_Mission(), "involvedActors", null, 0, -1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_InvolvedSystem(), this.getSystemMissionInvolvement(), this.getSystemMissionInvolvement_Mission(), "involvedSystem", null, 0, 1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMission_ExploitedCapabilities(), this.getCapability(), this.getCapability_PurposeMissions(), "exploitedCapabilities", null, 0, -1, Mission.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(missionPkgEClass, MissionPkg.class, "MissionPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMissionPkg_OwnedMissionPkgs(), this.getMissionPkg(), null, "ownedMissionPkgs", null, 0, -1, MissionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMissionPkg_OwnedMissions(), this.getMission(), null, "ownedMissions", null, 0, -1, MissionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemMissionInvolvementEClass, SystemMissionInvolvement.class, "SystemMissionInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSystemMissionInvolvement_Mission(), this.getMission(), this.getMission_InvolvedSystem(), "mission", null, 1, 1, SystemMissionInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemMissionInvolvement_System(), this.getSystem(), this.getSystem_ParticipationsInMissions(), "system", null, 1, 1, SystemMissionInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(capabilityEClass, Capability.class, "Capability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCapability_OwnedActorCapabilityInvolvements(), this.getActorCapabilityInvolvement(), null, "ownedActorCapabilityInvolvements", null, 0, -1, Capability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_OwnedSystemCapabilityInvolvement(), this.getSystemCapabilityInvolvement(), null, "ownedSystemCapabilityInvolvement", null, 0, 1, Capability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_InvolvedActors(), this.getActorCapabilityInvolvement(), this.getActorCapabilityInvolvement_Capability(), "involvedActors", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_InvolvedSystem(), this.getSystemCapabilityInvolvement(), this.getSystemCapabilityInvolvement_Capability(), "involvedSystem", null, 0, 1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_ParticipatingActors(), this.getActor(), this.getActor_ContributedCapabilities(), "participatingActors", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_ParticipatingSystem(), this.getSystem(), this.getSystem_ContributedCapabilities(), "participatingSystem", null, 0, 1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_Purposes(), this.getCapabilityExploitation(), this.getCapabilityExploitation_Capability(), "purposes", null, 0, -1, Capability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_PurposeMissions(), this.getMission(), this.getMission_ExploitedCapabilities(), "purposeMissions", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_RealizedOperationalCapabilities(), theOaPackage.getOperationalCapability(), theOaPackage.getOperationalCapability_RealizingCapabilities(), "realizedOperationalCapabilities", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapability_RealizingCapabilityRealizations(), theLaPackage.getCapabilityRealization(), theLaPackage.getCapabilityRealization_RealizedCapabilities(), "realizingCapabilityRealizations", null, 0, -1, Capability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(capabilityExploitationEClass, CapabilityExploitation.class, "CapabilityExploitation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCapabilityExploitation_Mission(), this.getMission(), this.getMission_OwnedCapabilityExploitations(), "mission", null, 1, 1, CapabilityExploitation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityExploitation_Capability(), this.getCapability(), this.getCapability_Purposes(), "capability", null, 1, 1, CapabilityExploitation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(capabilityPkgEClass, CapabilityPkg.class, "CapabilityPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCapabilityPkg_OwnedCapabilities(), this.getCapability(), null, "ownedCapabilities", null, 0, -1, CapabilityPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityPkg_OwnedCapabilityPkgs(), this.getCapabilityPkg(), null, "ownedCapabilityPkgs", null, 0, -1, CapabilityPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemCapabilityInvolvementEClass, SystemCapabilityInvolvement.class, "SystemCapabilityInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSystemCapabilityInvolvement_Capability(), this.getCapability(), this.getCapability_InvolvedSystem(), "capability", null, 1, 1, SystemCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSystemCapabilityInvolvement_System(), this.getSystem(), this.getSystem_ParticipationsInCapabilities(), "system", null, 1, 1, SystemCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(operationalActorRealizationEClass, OperationalActorRealization.class, "OperationalActorRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(operationalAnalysisRealizationEClass, OperationalAnalysisRealization.class, "OperationalAnalysisRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(operationalEntityRealizationEClass, OperationalEntityRealization.class, "OperationalEntityRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(systemContextEClass, SystemContext.class, "SystemContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.polarsys.org/kitalpha/dsl/2007/dslfactory
		createDslfactoryAnnotations();
		// http://www.polarsys.org/kitalpha/ecore/documentation
		createDocumentationAnnotations();
		// http://www.polarsys.org/capella/semantic
		createSemanticAnnotations();
		// http://www.polarsys.org/kitalpha/emde/1.0.0/extension
		createExtensionAnnotations();
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
	 * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/dsl/2007/dslfactory</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createDslfactoryAnnotations() {
		String source = "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory"; //$NON-NLS-1$	
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
		  (getSystemAnalysis_OwnedSystemContext(), 
		   source, 
		   new String[] {
			 "description", "the \"context\" for this architecture, e.g. the parts that make the environnement of the system (actors, ...) , plus the system itself\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedSystem(), 
		   source, 
		   new String[] {
			 "description", "Reference to the system component\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedActorPkg(), 
		   source, 
		   new String[] {
			 "description", "Link to a package that contains system analysis actors\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemEClass, 
		   source, 
		   new String[] {
			 "description", "An organized set of elements functioning as a unit.\r\n[source:SysML Glossary for SysML v1.0]\r\n\r\nAn element, with structure, that exhibits observable properties and behaviors.\r\n[source:UML for System Engineering RFP]\r\n\r\nSee UML-SysML block, part, component, item\r\n[source:Capella study]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "description", "the Capabilities to which this System contributes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the links to Capabilities to which this System contributes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedMissions(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the Missions to which this System contributes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the links to the Missions to which this System contributes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ExternalCommunication(), 
		   source, 
		   new String[] {
			 "description", "the list of communication links endpoints that are attached to this System\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_OwnedEntityRealizations(), 
		   source, 
		   new String[] {
			 "description", "the realization links from Operational entities to System entities, being owned by this System\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_AllocatedEntityRealizations(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the links from operational entities being realized by this System\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (actorEClass, 
		   source, 
		   new String[] {
			 "description", "Specifies the role played by a user or any other system that interacts with the subject\r\n[source: SysML glossary for SysML v1.0]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "../img/usage_examples/example_actors_interfaces.png", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the list of links between this actor and the Missions in which it is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the list of links between this actor and the Capabilities in which it is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the list of links between this actor and the CapabilityRealization in which it is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedMissions(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the list of Missions in which this actor is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the list of Capabilities in which this actor is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_SystemCommunication(), 
		   source, 
		   new String[] {
			 "description", "the communication link endpoint that is attached to this Actor\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_OwnedOperationalActorRealizations(), 
		   source, 
		   new String[] {
			 "description", "the list of realisation links to/from operational-level actor(s) that this system actor hosts/contains", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_OwnedOperationalEntityRealizations(), 
		   source, 
		   new String[] {
			 "description", "the list of realisation links to/from operational-level entity(ies) that this system actor hosts/contains", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "description", "Link between a system actor and a system capability that means the actor is involved in the capability\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "../img/usage_examples/example_actor_capability.png", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "description", "Link to a system actor that is involved in the system capability.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "description", "Link to the system capability involving the actor\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "description", "Link between a system actor and a system mission that means the actor is involved in the mission\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "description", "Link to a system actor that is involved in the system mission\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "description", "Link to the system mission related to the actor\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorPkgEClass, 
		   source, 
		   new String[] {
			 "description", "Package that contains system actors\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActors(), 
		   source, 
		   new String[] {
			 "description", "Set of system actors that are defined at that level of package\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActorPkgs(), 
		   source, 
		   new String[] {
			 "description", "Sub pakages that contain system actors\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedSystemCommunication(), 
		   source, 
		   new String[] {
			 "description", "the SystemCommunication links contained in this Actor package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getMission_OwnedActorMissionInvolvements(), 
		   source, 
		   new String[] {
			 "description", "the links between Actors and Missions that are owned by this Mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedSystemMissionInvolvement(), 
		   source, 
		   new String[] {
			 "description", "the links between Missions and the System that are owned by this Mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getMission_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "description", "(computed automatically) the Actors involved in this Mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_ParticipatingSystem(), 
		   source, 
		   new String[] {
			 "description", "the System involved in this Mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedActors(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the links to Actors that are involved in this Mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the link to the System involved in this mission\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "description", "Link between a system and a mission meaning that the system is involved in the mission\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "description", "Link to a Mission that the System is involved in\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_System(), 
		   source, 
		   new String[] {
			 "description", "Link to a system that is involved in the system mission\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getCapability_OwnedActorCapabilityInvolvements(), 
		   source, 
		   new String[] {
			 "description", "the links between Actors and Capabilities that are owned by this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_OwnedSystemCapabilityInvolvement(), 
		   source, 
		   new String[] {
			 "description", "the links between the System and Capabilities, that are owned by this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedActors(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the links between Actors and this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the link to the System being involved in this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the Actors involved with this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_ParticipatingSystem(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the System involved in this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "description", "Link between a system and a system capability that means the system is involved in the capability\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "description", "the Capability involved in this relationship\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_System(), 
		   source, 
		   new String[] {
			 "description", "The System involved in this relationship\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (operationalActorRealizationEClass, 
		   source, 
		   new String[] {
			 "description", "Realization link between a system actor and an operational actor\r\n\r\nRealization is a specialized abstraction relationship between two sets of model elements, one representing a specification\r\n(the supplier) and the other represents an implementation of the latter (the client). Realization can be used to model\r\nstepwise refinement, optimizations, transformations, templates, model synthesis, framework composition, etc.\r\n[source:UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (operationalEntityRealizationEClass, 
		   source, 
		   new String[] {
			 "description", "Realization link between a system and an operational entity\r\n[source:Capella study]\r\n\r\nRealization is a specialized abstraction relationship between two sets of model elements, one representing a specification\r\n(the supplier) and the other represents an implementation of the latter (the client). Realization can be used to model\r\nstepwise refinement, optimizations, transformations, templates, model synthesis, framework composition, etc.\r\n[source:UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemContextEClass, 
		   source, 
		   new String[] {
			 "description", "the list of Parts that define the System and its environment, at this abstraction level.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "system", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemAnalysisEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedSystemContext(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedActorPkg(), 
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
		  (systemEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedMissions(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_AllocatedSystemFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_RealizedEntities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_RealizingLogicalComponents(), 
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
		  (getSystemFunction_AllocatorActors(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemFunction_AllocatorSystems(), 
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
		  (actorEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ContributedMissions(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_AllocatedSystemFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_RealizedEntities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_RealizedOperationalActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_RealizingLogicalActors(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorPkgEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorPkg_OwnedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorPkg_OwnedActorPkgs(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (missionEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ParticipatingSystem(), 
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
		  (getCapability_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_ParticipatingSystem(), 
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
		  (systemContextEClass, 
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
		  (getSystemAnalysis_OwnedSystem(), 
		   source, 
		   new String[] {
			 "Label", "ownedSystem" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedActorPkg(), 
		   source, 
		   new String[] {
			 "Label", "ownedActorPkg" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedMissionPkg(), 
		   source, 
		   new String[] {
			 "Label", "ownedMissionPkg" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemEClass, 
		   source, 
		   new String[] {
			 "Label", "System" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "Label", "contributedCapabilitySpecificationUseCases" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "Label", "participationsInCapabilities" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedMissions(), 
		   source, 
		   new String[] {
			 "Label", "contributedMissions" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "Label", "participationsInMissions" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ExternalCommunication(), 
		   source, 
		   new String[] {
			 "Label", "externalCommunications" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (actorEClass, 
		   source, 
		   new String[] {
			 "Label", "Actor" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "Label", "participationsInMissions" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "Label", "participationsInCapabilities" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "Label", "participationsInCapabilityRealizations" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedMissions(), 
		   source, 
		   new String[] {
			 "Label", "contributedMissions" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "Label", "contributedCapabilitySpecificationUseCases" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_SystemCommunication(), 
		   source, 
		   new String[] {
			 "Label", "systemCommunication" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "Label", "ActorCapabilityInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "Label", "actor" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "Label", "capability" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "Label", "ActorMissionInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "Label", "actor" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "Label", "mission" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorPkgEClass, 
		   source, 
		   new String[] {
			 "Label", "ActorPkg" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActors(), 
		   source, 
		   new String[] {
			 "Label", "ownedActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActorPkgs(), 
		   source, 
		   new String[] {
			 "Label", "subActorPkgs" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedSystemCommunication(), 
		   source, 
		   new String[] {
			 "Label", "ownedSystemCommunication" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (missionEClass, 
		   source, 
		   new String[] {
			 "Label", "Mission" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedActorMissionInvolvements(), 
		   source, 
		   new String[] {
			 "Label", "ownedActorMissionInvolvements" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedSystemMissionInvolvement(), 
		   source, 
		   new String[] {
			 "Label", "ownedMissionSupplierLinks" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedCapabilityExploitations(), 
		   source, 
		   new String[] {
			 "Label", "capabilityExploitations" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "Label", "participatingActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_ParticipatingSystem(), 
		   source, 
		   new String[] {
			 "Label", "participatingSystems" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedActors(), 
		   source, 
		   new String[] {
			 "Label", "invovledActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "Label", "missionSupplierLinks" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "Label", "MissionSupplierLink" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "Label", "mission" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_System(), 
		   source, 
		   new String[] {
			 "Label", "system" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (capabilityEClass, 
		   source, 
		   new String[] {
			 "Label", "Capability" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_OwnedActorCapabilityInvolvements(), 
		   source, 
		   new String[] {
			 "Label", "ownedActorCapabilityInvolvements" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_OwnedSystemCapabilityInvolvement(), 
		   source, 
		   new String[] {
			 "Label", "ownedCapabilitySupplierLinks" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedActors(), 
		   source, 
		   new String[] {
			 "Label", "involvedActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "Label", "capabilitySupplierLinks" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "Label", "participatingActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_ParticipatingSystem(), 
		   source, 
		   new String[] {
			 "Label", "participatingSystems" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "Label", "CapabilitySupplierLink" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "Label", "capability" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_System(), 
		   source, 
		   new String[] {
			 "Label", "system" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getSystemAnalysis_OwnedSystem(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedActorPkg(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedMissionPkg(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.System" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ExternalCommunication(), 
		   source, 
		   new String[] {
			 "featureName", "ownedAttribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (actorEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.Actor" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_SystemCommunication(), 
		   source, 
		   new String[] {
			 "featureName", "ownedAttribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.ActorCapabilityInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.ActorMissionInvolvement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorPkgEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.ActorPkg" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActors(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActorPkgs(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedSystemCommunication(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (missionEClass, 
		   source, 
		   new String[] {
			 "metaclass", "UseCase", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.Mission" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedActorMissionInvolvements(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedSystemMissionInvolvement(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedCapabilityExploitations(), 
		   source, 
		   new String[] {
			 "featureName", "include", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "UseCase" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedActors(), 
		   source, 
		   new String[] {
			 "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.MissionSupplierLink" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_System(), 
		   source, 
		   new String[] {
			 "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (capabilityEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.Capability" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_OwnedActorCapabilityInvolvements(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_OwnedSystemCapabilityInvolvement(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedActors(), 
		   source, 
		   new String[] {
			 "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.CapabilitySupplierLink" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_System(), 
		   source, 
		   new String[] {
			 "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getSystemAnalysis_OwnedSystemContext(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::PackagedElement elements on which SystemContext stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedSystem(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElements elements on which System stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedActorPkg(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::nestedPackage elements on which ActorPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Blocks::Block", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "uml::Component is not part of UML4SysML, and should therefore not be used \r\nfor implementing a SysML profile", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ContributedMissions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_ExternalCommunication(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_OwnedEntityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Blocks cannot contain Realizations : store them in the nearest available package", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "Order is not preserved in the UML model." //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_AllocatedEntityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_AllocatedSystemFunctions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_RealizedEntities(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystem_RealizingLogicalComponents(), 
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
		  (getSystemFunction_AllocatorActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemFunction_AllocatorSystems(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (actorEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Blocks::Block", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInMissions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedMissions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_ContributedCapabilities(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_SystemCommunication(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_OwnedOperationalActorRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which OperationalActorRealizations stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_OwnedOperationalEntityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which OperationalActorRealizations stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_AllocatedSystemFunctions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_RealizedEntities(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_RealizedOperationalActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActor_RealizingLogicalActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Actor(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (actorPkgEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which Actor stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedActorPkgs(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::nestedPackage elements on which ActorPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getActorPkg_OwnedSystemCommunication(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getMission_OwnedActorMissionInvolvements(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "Some elements on which ActorMissionInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_OwnedSystemMissionInvolvement(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "Some elements on which SystemMissionInvolvement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getMission_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_ParticipatingSystem(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getMission_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemMissionInvolvementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_System(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getCapability_OwnedActorCapabilityInvolvements(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which ActorCapabilityInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_OwnedSystemCapabilityInvolvement(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which SystemCapabilityInvolvement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_InvolvedSystem(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapability_ParticipatingSystem(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_System(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (operationalActorRealizationEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (operationalEntityRealizationEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemContextEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getSystemAnalysis_OwnedSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedActorPkg(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemAnalysis_OwnedMissionPkg(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ContributedCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ContributedMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ParticipationsInMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ExternalCommunication(), 
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
		  (getActor_ParticipationsInMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ContributedMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ContributedCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_SystemCommunication(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Actor(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Actor(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorPkg_OwnedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorPkg_OwnedActorPkgs(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorPkg_OwnedSystemCommunication(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_OwnedActorMissionInvolvements(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_OwnedSystemMissionInvolvement(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_OwnedCapabilityExploitations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ParticipatingSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_InvolvedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_InvolvedSystem(), 
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
		  (getSystemMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_System(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_OwnedActorCapabilityInvolvements(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_OwnedSystemCapabilityInvolvement(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_InvolvedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_InvolvedSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_ParticipatingSystem(), 
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
		addAnnotation
		  (getSystemCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_System(), 
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
		   });	
		addAnnotation
		  (getSystemAnalysis_ContainedSystemFunctionPkg(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemAnalysis_AllocatedOperationalAnalysisRealizations(), 
		   source, 
		   new String[] {
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
		   });	
		addAnnotation
		  (getSystem_ContributedCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ContributedMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_ParticipationsInMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_AllocatedEntityRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_AllocatedSystemFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_RealizedEntities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystem_RealizingLogicalComponents(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemFunction_AllocatorActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemFunction_AllocatorSystems(), 
		   source, 
		   new String[] {
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
		   });	
		addAnnotation
		  (getSystemFunction_ContainedSystemFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemFunction_ChildrenSystemFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ParticipationsInMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ContributedMissions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_ContributedCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_AllocatedSystemFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_RealizedEntities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_RealizedOperationalActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActor_RealizingLogicalActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Actor(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Actor(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getActorMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ParticipatingSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_InvolvedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_InvolvedSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getMission_ExploitedCapabilities(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_Mission(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemMissionInvolvement_System(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_InvolvedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_InvolvedSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_ParticipatingSystem(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapability_PurposeMissions(), 
		   source, 
		   new String[] {
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
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_Capability(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getSystemCapabilityInvolvement_System(), 
		   source, 
		   new String[] {
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
		  (actorCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (actorMissionInvolvementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemMissionInvolvementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (capabilityExploitationEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemCapabilityInvolvementEClass, 
		   source, 
		   new String[] {
		   });
	}

} //CtxPackageImpl
