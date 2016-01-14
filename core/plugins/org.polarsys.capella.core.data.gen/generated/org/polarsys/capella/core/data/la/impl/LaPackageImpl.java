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
package org.polarsys.capella.core.data.la.impl;

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
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.la.SystemRealization;
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
	private EClass systemRealizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextInterfaceRealizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalActorPkgEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalActorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemActorRealizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalContextEClass = null;

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
		LaPackageImpl theLaPackage = (LaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LaPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ActivityPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CapellamodellerPackageImpl theCapellamodellerPackage = (CapellamodellerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI) instanceof CapellamodellerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI) : CapellamodellerPackage.eINSTANCE);
		CapellacorePackageImpl theCapellacorePackage = (CapellacorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI) instanceof CapellacorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI) : CapellacorePackage.eINSTANCE);
		OaPackageImpl theOaPackage = (OaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI) instanceof OaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI) : OaPackage.eINSTANCE);
		CtxPackageImpl theCtxPackage = (CtxPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI) instanceof CtxPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI) : CtxPackage.eINSTANCE);
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
		theLaPackage.createPackageContents();
		theCapellamodellerPackage.createPackageContents();
		theCapellacorePackage.createPackageContents();
		theOaPackage.createPackageContents();
		theCtxPackage.createPackageContents();
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
		theLaPackage.initializePackageContents();
		theCapellamodellerPackage.initializePackageContents();
		theCapellacorePackage.initializePackageContents();
		theOaPackage.initializePackageContents();
		theCtxPackage.initializePackageContents();
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
	public EClass getLogicalArchitecturePkg() {
		return logicalArchitecturePkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecturePkg_OwnedLogicalArchitectures() {
		return (EReference)logicalArchitecturePkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalArchitecture() {
		return logicalArchitectureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_OwnedLogicalContext() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_OwnedLogicalComponent() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_OwnedLogicalComponentPkg() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_OwnedLogicalActorPkg() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_ContainedCapabilityRealizationPkg() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_ContainedLogicalFunctionPkg() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_OwnedSystemAnalysisRealizations() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_AllocatedSystemAnalysisRealizations() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_AllocatedSystemAnalyses() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalArchitecture_AllocatingPhysicalArchitectures() {
		return (EReference)logicalArchitectureEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalFunction() {
		return logicalFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_OwnedLogicalFunctionPkgs() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_AllocatorLogicalActors() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_AllocatorLogicalComponents() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_RealizedSystemFunctions() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_RealizingPhysicalFunctions() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_ContainedLogicalFunctions() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunction_ChildrenLogicalFunctions() {
		return (EReference)logicalFunctionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalFunctionPkg() {
		return logicalFunctionPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunctionPkg_OwnedLogicalFunctions() {
		return (EReference)logicalFunctionPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalFunctionPkg_OwnedLogicalFunctionPkgs() {
		return (EReference)logicalFunctionPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalComponent() {
		return logicalComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_OwnedLogicalComponents() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_OwnedLogicalArchitectures() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_OwnedLogicalComponentPkgs() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_OwnedSystemRealizations() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_SystemRealizations() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_SubLogicalComponents() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_AllocatedLogicalFunctions() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_RealizingPhysicalComponents() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponent_RealizedSystems() {
		return (EReference)logicalComponentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalComponentPkg() {
		return logicalComponentPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponentPkg_OwnedLogicalComponents() {
		return (EReference)logicalComponentPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalComponentPkg_OwnedLogicalComponentPkgs() {
		return (EReference)logicalComponentPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapabilityRealization() {
		return capabilityRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_OwnedActorCapabilityRealizations() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_OwnedSystemComponentCapabilityRealizations() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_ParticipatingActors() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_ParticipatingSystemComponents() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_InvolvedActors() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_InvolvedSystemComponents() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_RealizedCapabilities() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_RealizedCapabilityRealizations() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealization_RealizingCapabilityRealizations() {
		return (EReference)capabilityRealizationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCapabilityRealizationPkg() {
		return capabilityRealizationPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealizationPkg_OwnedCapabilityRealizations() {
		return (EReference)capabilityRealizationPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs() {
		return (EReference)capabilityRealizationPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemAnalysisRealization() {
		return systemAnalysisRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemRealization() {
		return systemRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextInterfaceRealization() {
		return contextInterfaceRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalActorPkg() {
		return logicalActorPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActorPkg_OwnedLogicalActorPkgs() {
		return (EReference)logicalActorPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActorPkg_OwnedLogicalActors() {
		return (EReference)logicalActorPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalActor() {
		return logicalActorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActor_OwnedSystemActorRealizations() {
		return (EReference)logicalActorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActor_SystemActorRealizations() {
		return (EReference)logicalActorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActor_ParticipationsInCapabilityRealizations() {
		return (EReference)logicalActorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActor_AllocatedLogicalFunctions() {
		return (EReference)logicalActorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActor_RealizedSystemActors() {
		return (EReference)logicalActorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalActor_RealizingPhysicalActors() {
		return (EReference)logicalActorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemActorRealization() {
		return systemActorRealizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalContext() {
		return logicalContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES);
		createEReference(logicalArchitectureEClass, LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES);

		logicalFunctionEClass = createEClass(LOGICAL_FUNCTION);
		createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS);
		createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__ALLOCATOR_LOGICAL_ACTORS);
		createEReference(logicalFunctionEClass, LOGICAL_FUNCTION__ALLOCATOR_LOGICAL_COMPONENTS);
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
		createEReference(logicalComponentEClass, LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS);
		createEReference(logicalComponentEClass, LOGICAL_COMPONENT__SYSTEM_REALIZATIONS);
		createEReference(logicalComponentEClass, LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS);
		createEReference(logicalComponentEClass, LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS);
		createEReference(logicalComponentEClass, LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS);
		createEReference(logicalComponentEClass, LOGICAL_COMPONENT__REALIZED_SYSTEMS);

		logicalComponentPkgEClass = createEClass(LOGICAL_COMPONENT_PKG);
		createEReference(logicalComponentPkgEClass, LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS);
		createEReference(logicalComponentPkgEClass, LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS);

		capabilityRealizationEClass = createEClass(CAPABILITY_REALIZATION);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__PARTICIPATING_ACTORS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__INVOLVED_ACTORS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__REALIZED_CAPABILITIES);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS);
		createEReference(capabilityRealizationEClass, CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS);

		capabilityRealizationPkgEClass = createEClass(CAPABILITY_REALIZATION_PKG);
		createEReference(capabilityRealizationPkgEClass, CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS);
		createEReference(capabilityRealizationPkgEClass, CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS);

		systemAnalysisRealizationEClass = createEClass(SYSTEM_ANALYSIS_REALIZATION);

		systemRealizationEClass = createEClass(SYSTEM_REALIZATION);

		contextInterfaceRealizationEClass = createEClass(CONTEXT_INTERFACE_REALIZATION);

		logicalActorPkgEClass = createEClass(LOGICAL_ACTOR_PKG);
		createEReference(logicalActorPkgEClass, LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS);
		createEReference(logicalActorPkgEClass, LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS);

		logicalActorEClass = createEClass(LOGICAL_ACTOR);
		createEReference(logicalActorEClass, LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS);
		createEReference(logicalActorEClass, LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS);
		createEReference(logicalActorEClass, LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS);
		createEReference(logicalActorEClass, LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS);
		createEReference(logicalActorEClass, LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS);
		createEReference(logicalActorEClass, LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS);

		systemActorRealizationEClass = createEClass(SYSTEM_ACTOR_REALIZATION);

		logicalContextEClass = createEClass(LOGICAL_CONTEXT);
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
		InteractionPackage theInteractionPackage = (InteractionPackage)EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
		CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		logicalArchitecturePkgEClass.getESuperTypes().add(theCsPackage.getBlockArchitecturePkg());
		logicalArchitectureEClass.getESuperTypes().add(theCsPackage.getComponentArchitecture());
		logicalFunctionEClass.getESuperTypes().add(theFaPackage.getAbstractFunction());
		logicalFunctionPkgEClass.getESuperTypes().add(theFaPackage.getFunctionPkg());
		logicalComponentEClass.getESuperTypes().add(theCsPackage.getSystemComponent());
		logicalComponentPkgEClass.getESuperTypes().add(theFaPackage.getAbstractFunctionalStructure());
		capabilityRealizationEClass.getESuperTypes().add(theInteractionPackage.getAbstractCapability());
		capabilityRealizationPkgEClass.getESuperTypes().add(theCapellacommonPackage.getAbstractCapabilityPkg());
		systemAnalysisRealizationEClass.getESuperTypes().add(theCsPackage.getArchitectureAllocation());
		systemRealizationEClass.getESuperTypes().add(theCsPackage.getComponentAllocation());
		contextInterfaceRealizationEClass.getESuperTypes().add(theCsPackage.getInterfaceAllocation());
		logicalActorPkgEClass.getESuperTypes().add(theFaPackage.getAbstractFunctionalStructure());
		logicalActorEClass.getESuperTypes().add(theCsPackage.getAbstractActor());
		systemActorRealizationEClass.getESuperTypes().add(theCsPackage.getComponentAllocation());
		logicalContextEClass.getESuperTypes().add(theCsPackage.getComponentContext());

		// Initialize classes and features; add operations and parameters
		initEClass(logicalArchitecturePkgEClass, LogicalArchitecturePkg.class, "LogicalArchitecturePkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLogicalArchitecturePkg_OwnedLogicalArchitectures(), this.getLogicalArchitecture(), null, "ownedLogicalArchitectures", null, 0, -1, LogicalArchitecturePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(logicalArchitectureEClass, LogicalArchitecture.class, "LogicalArchitecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_OwnedLogicalContext(), this.getLogicalContext(), null, "ownedLogicalContext", null, 0, 1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_OwnedLogicalComponent(), this.getLogicalComponent(), null, "ownedLogicalComponent", null, 0, 1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_OwnedLogicalComponentPkg(), this.getLogicalComponentPkg(), null, "ownedLogicalComponentPkg", null, 0, 1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_OwnedLogicalActorPkg(), this.getLogicalActorPkg(), null, "ownedLogicalActorPkg", null, 0, 1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_ContainedCapabilityRealizationPkg(), this.getCapabilityRealizationPkg(), null, "containedCapabilityRealizationPkg", null, 0, 1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_ContainedLogicalFunctionPkg(), this.getLogicalFunctionPkg(), null, "containedLogicalFunctionPkg", null, 0, 1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_OwnedSystemAnalysisRealizations(), this.getSystemAnalysisRealization(), null, "ownedSystemAnalysisRealizations", null, 0, -1, LogicalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_AllocatedSystemAnalysisRealizations(), this.getSystemAnalysisRealization(), null, "allocatedSystemAnalysisRealizations", null, 0, -1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_AllocatedSystemAnalyses(), theCtxPackage.getSystemAnalysis(), theCtxPackage.getSystemAnalysis_AllocatingLogicalArchitectures(), "allocatedSystemAnalyses", null, 0, -1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalArchitecture_AllocatingPhysicalArchitectures(), thePaPackage.getPhysicalArchitecture(), thePaPackage.getPhysicalArchitecture_AllocatedLogicalArchitectures(), "allocatingPhysicalArchitectures", null, 0, -1, LogicalArchitecture.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(logicalFunctionEClass, LogicalFunction.class, "LogicalFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLogicalFunction_OwnedLogicalFunctionPkgs(), this.getLogicalFunctionPkg(), null, "ownedLogicalFunctionPkgs", null, 0, -1, LogicalFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalFunction_AllocatorLogicalActors(), this.getLogicalActor(), this.getLogicalActor_AllocatedLogicalFunctions(), "allocatorLogicalActors", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalFunction_AllocatorLogicalComponents(), this.getLogicalComponent(), this.getLogicalComponent_AllocatedLogicalFunctions(), "allocatorLogicalComponents", null, 0, -1, LogicalFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
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
		initEReference(getLogicalComponent_OwnedSystemRealizations(), this.getSystemRealization(), null, "ownedSystemRealizations", null, 0, -1, LogicalComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalComponent_SystemRealizations(), this.getSystemRealization(), null, "systemRealizations", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalComponent_SubLogicalComponents(), this.getLogicalComponent(), null, "subLogicalComponents", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalComponent_AllocatedLogicalFunctions(), this.getLogicalFunction(), this.getLogicalFunction_AllocatorLogicalComponents(), "allocatedLogicalFunctions", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalComponent_RealizingPhysicalComponents(), thePaPackage.getPhysicalComponent(), thePaPackage.getPhysicalComponent_RealizedLogicalComponents(), "realizingPhysicalComponents", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalComponent_RealizedSystems(), theCtxPackage.getSystem(), theCtxPackage.getSystem_RealizingLogicalComponents(), "realizedSystems", null, 0, -1, LogicalComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(logicalComponentPkgEClass, LogicalComponentPkg.class, "LogicalComponentPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLogicalComponentPkg_OwnedLogicalComponents(), this.getLogicalComponent(), null, "ownedLogicalComponents", null, 0, -1, LogicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalComponentPkg_OwnedLogicalComponentPkgs(), this.getLogicalComponentPkg(), null, "ownedLogicalComponentPkgs", null, 0, -1, LogicalComponentPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(capabilityRealizationEClass, CapabilityRealization.class, "CapabilityRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCapabilityRealization_OwnedActorCapabilityRealizations(), theCsPackage.getActorCapabilityRealizationInvolvement(), null, "ownedActorCapabilityRealizations", null, 0, -1, CapabilityRealization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_OwnedSystemComponentCapabilityRealizations(), theCsPackage.getSystemComponentCapabilityRealizationInvolvement(), null, "ownedSystemComponentCapabilityRealizations", null, 0, -1, CapabilityRealization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_ParticipatingActors(), theCsPackage.getAbstractActor(), null, "participatingActors", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_ParticipatingSystemComponents(), theCsPackage.getSystemComponent(), null, "participatingSystemComponents", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_InvolvedActors(), theCsPackage.getActorCapabilityRealizationInvolvement(), null, "involvedActors", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_InvolvedSystemComponents(), theCsPackage.getSystemComponentCapabilityRealizationInvolvement(), null, "involvedSystemComponents", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_RealizedCapabilities(), theCtxPackage.getCapability(), theCtxPackage.getCapability_RealizingCapabilityRealizations(), "realizedCapabilities", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_RealizedCapabilityRealizations(), this.getCapabilityRealization(), this.getCapabilityRealization_RealizingCapabilityRealizations(), "realizedCapabilityRealizations", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealization_RealizingCapabilityRealizations(), this.getCapabilityRealization(), this.getCapabilityRealization_RealizedCapabilityRealizations(), "realizingCapabilityRealizations", null, 0, -1, CapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(capabilityRealizationPkgEClass, CapabilityRealizationPkg.class, "CapabilityRealizationPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCapabilityRealizationPkg_OwnedCapabilityRealizations(), this.getCapabilityRealization(), null, "ownedCapabilityRealizations", null, 0, -1, CapabilityRealizationPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs(), this.getCapabilityRealizationPkg(), null, "ownedCapabilityRealizationPkgs", null, 0, -1, CapabilityRealizationPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemAnalysisRealizationEClass, SystemAnalysisRealization.class, "SystemAnalysisRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(systemRealizationEClass, SystemRealization.class, "SystemRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(contextInterfaceRealizationEClass, ContextInterfaceRealization.class, "ContextInterfaceRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(logicalActorPkgEClass, LogicalActorPkg.class, "LogicalActorPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLogicalActorPkg_OwnedLogicalActorPkgs(), this.getLogicalActorPkg(), null, "ownedLogicalActorPkgs", null, 0, -1, LogicalActorPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalActorPkg_OwnedLogicalActors(), this.getLogicalActor(), null, "ownedLogicalActors", null, 0, -1, LogicalActorPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(logicalActorEClass, LogicalActor.class, "LogicalActor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLogicalActor_OwnedSystemActorRealizations(), this.getSystemActorRealization(), null, "ownedSystemActorRealizations", null, 0, -1, LogicalActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalActor_SystemActorRealizations(), this.getSystemActorRealization(), null, "systemActorRealizations", null, 0, -1, LogicalActor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalActor_ParticipationsInCapabilityRealizations(), theCsPackage.getActorCapabilityRealizationInvolvement(), null, "participationsInCapabilityRealizations", null, 0, -1, LogicalActor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalActor_AllocatedLogicalFunctions(), this.getLogicalFunction(), this.getLogicalFunction_AllocatorLogicalActors(), "allocatedLogicalFunctions", null, 0, -1, LogicalActor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalActor_RealizedSystemActors(), theCtxPackage.getActor(), theCtxPackage.getActor_RealizingLogicalActors(), "realizedSystemActors", null, 0, -1, LogicalActor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLogicalActor_RealizingPhysicalActors(), thePaPackage.getPhysicalActor(), thePaPackage.getPhysicalActor_RealizedLogicalActors(), "realizingPhysicalActors", null, 0, -1, LogicalActor.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemActorRealizationEClass, SystemActorRealization.class, "SystemActorRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(logicalContextEClass, LogicalContext.class, "LogicalContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
		  (getLogicalArchitecture_OwnedLogicalContext(), 
		   source, 
		   new String[] {
			 "description", "the set of parts that make the \"world\" (the system + the external parts) at the logical level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponent(), 
		   source, 
		   new String[] {
			 "description", "the logical component that represents the system (in the case where a single System is being designed)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalArchitecture_OwnedLogicalActorPkg(), 
		   source, 
		   new String[] {
			 "description", "Link to the package that contains logical actors\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalComponent_OwnedSystemRealizations(), 
		   source, 
		   new String[] {
			 "description", "the system realization links that are contained in this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalComponent_SystemRealizations(), 
		   source, 
		   new String[] {
			 "description", "the system realization links that point to/from this component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getCapabilityRealization_OwnedActorCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "description", "the actor capability realization links that are contained in this CapabilityRealization\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_OwnedSystemComponentCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "description", "the system component realization links that are contained/owned by this capability realization\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) direct references to the actors which capabilities are being realized by this CapabilityRealization\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingSystemComponents(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) direct references to the system components being realized by this CapabilityRealization\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedActors(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) references to the involvement links in ActorCapabilityRealizations\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedSystemComponents(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) references to the involvement links in SystemComponentRealizations for this CapabilityRealization\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemRealizationEClass, 
		   source, 
		   new String[] {
			 "description", "an allocation link between a system and (one of) the component(s) that realize it\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
		addAnnotation
		  (logicalActorPkgEClass, 
		   source, 
		   new String[] {
			 "description", "Package that contains LogicalActor elements", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActorPkg_OwnedLogicalActorPkgs(), 
		   source, 
		   new String[] {
			 "description", "sub-(logical actor) packages contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActorPkg_OwnedLogicalActors(), 
		   source, 
		   new String[] {
			 "description", "logical actors contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (logicalActorEClass, 
		   source, 
		   new String[] {
			 "description", "External actor interacting with the system via logical interfaces", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "logical actors are typically created automatically when performing a transition of system-level actors. Additional logical actors can then be created manually.", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_OwnedSystemActorRealizations(), 
		   source, 
		   new String[] {
			 "description", "the list of realisation links to/from system-level actor(s) that this actor hosts/contains\r\n[source: Capella study]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_SystemActorRealizations(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the list of actor realization links where this logical actor is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "description", "(automatically computed) the involvement links between this logical actor and actor capability realizations\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemActorRealizationEClass, 
		   source, 
		   new String[] {
			 "description", "an allocation link between an actor, and the component that realizes it\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "logical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (logicalContextEClass, 
		   source, 
		   new String[] {
			 "description", "the list of parts that together make the \"world\" (system + external parts) at the logical architecture level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		  (getLogicalArchitecture_OwnedLogicalContext(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponent(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponentPkg(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalActorPkg(), 
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
		  (getLogicalFunction_AllocatorLogicalActors(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalFunction_AllocatorLogicalComponents(), 
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
		  (getLogicalComponent_RealizingPhysicalComponents(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalComponent_RealizedSystems(), 
		   source, 
		   new String[] {
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
		  (getCapabilityRealization_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingSystemComponents(), 
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
		addAnnotation
		  (logicalActorPkgEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActorPkg_OwnedLogicalActorPkgs(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActorPkg_OwnedLogicalActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (logicalActorEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_AllocatedLogicalFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_RealizedSystemActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_RealizingPhysicalActors(), 
		   source, 
		   new String[] {
			 "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (logicalContextEClass, 
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
		  (getLogicalArchitecture_OwnedLogicalComponent(), 
		   source, 
		   new String[] {
			 "Label", "ownedLogicalComponents" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponentPkg(), 
		   source, 
		   new String[] {
			 "Label", "ownedLogicalComponentPkgs" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalActorPkg(), 
		   source, 
		   new String[] {
			 "Label", "ownedActorPkg" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getCapabilityRealization_OwnedActorCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "Label", "ownedActorCapabilityRealizations" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_OwnedSystemComponentCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "Label", "ownedSystemComponentCapabilityRealizations" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "Label", "participatingActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingSystemComponents(), 
		   source, 
		   new String[] {
			 "Label", "participatingSystemComponents" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedActors(), 
		   source, 
		   new String[] {
			 "Label", "involvedActors" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedSystemComponents(), 
		   source, 
		   new String[] {
			 "Label", "involvedSystemComponents" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalArchitecture_OwnedLogicalComponent(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponentPkg(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalActorPkg(), 
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
		  (getCapabilityRealization_OwnedActorCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_OwnedSystemComponentCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedActors(), 
		   source, 
		   new String[] {
			 "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedSystemComponents(), 
		   source, 
		   new String[] {
			 "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalArchitecture_OwnedLogicalContext(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which LogicalContext stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponent(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which LogicalComponent stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalArchitecture_OwnedLogicalActorPkg(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::nestedPackage elements on which LogicalActorPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalFunction_AllocatorLogicalActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalFunction_AllocatorLogicalComponents(), 
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
		  (getLogicalComponent_OwnedSystemRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "SysML::Blocks::Block cannot contain realizations, hence we find the nearest available package to store them.", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalComponent_SystemRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getLogicalComponent_RealizingPhysicalComponents(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalComponent_RealizedSystems(), 
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
		  (getCapabilityRealization_OwnedActorCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which ActorCapabilityRealizationInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_OwnedSystemComponentCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which SystemComponentCapabilityRealizationInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingSystemComponents(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedSystemComponents(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (systemRealizationEClass, 
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
		addAnnotation
		  (logicalActorPkgEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActorPkg_OwnedLogicalActorPkgs(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::nestedPackage elements on which LogicalActorPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActorPkg_OwnedLogicalActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which LogicalActor stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (logicalActorEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Blocks::Block", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_OwnedSystemActorRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::BehavioredClassifier::interfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::BehavioredClassifier::interfaceRealization elements on which ActorRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_SystemActorRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_AllocatedLogicalFunctions(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_RealizedSystemActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getLogicalActor_RealizingPhysicalActors(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemActorRealizationEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::InterfaceRealization", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (logicalContextEClass, 
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
		  (getLogicalArchitecturePkg_OwnedLogicalArchitectures(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponent(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalComponentPkg(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_OwnedLogicalActorPkg(), 
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
		  (getCapabilityRealization_OwnedActorCapabilityRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_OwnedSystemComponentCapabilityRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingSystemComponents(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedSystemComponents(), 
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
		   });	
		addAnnotation
		  (getLogicalArchitecture_ContainedLogicalFunctionPkg(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalArchitecture_AllocatedSystemAnalysisRealizations(), 
		   source, 
		   new String[] {
			 "derive", "self.ownedPartitions.representedElement.oclIsKindOf(PhysicalComponent) -> oclAsType(PhysicalComponent)" //$NON-NLS-1$ //$NON-NLS-2$
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
		   });	
		addAnnotation
		  (getLogicalFunction_AllocatorLogicalActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalFunction_AllocatorLogicalComponents(), 
		   source, 
		   new String[] {
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
		   });	
		addAnnotation
		  (getLogicalFunction_ContainedLogicalFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalFunction_ChildrenLogicalFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalComponent_SystemRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalComponent_SubLogicalComponents(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalComponent_AllocatedLogicalFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalComponent_RealizingPhysicalComponents(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalComponent_RealizedSystems(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_ParticipatingSystemComponents(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getCapabilityRealization_InvolvedSystemComponents(), 
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
		   });	
		addAnnotation
		  (getLogicalActor_SystemActorRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_ParticipationsInCapabilityRealizations(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_AllocatedLogicalFunctions(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_RealizedSystemActors(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getLogicalActor_RealizingPhysicalActors(), 
		   source, 
		   new String[] {
		   });
	}

} //LaPackageImpl
